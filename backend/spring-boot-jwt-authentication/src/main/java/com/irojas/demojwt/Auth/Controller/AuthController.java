package com.irojas.demojwt.Auth.Controller;

import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.Model.TemporaryUser;
import com.irojas.demojwt.Auth.ModelDTO.AuthResponse;
import com.irojas.demojwt.Auth.ModelDTO.ChangeUserData;
import com.irojas.demojwt.Auth.ModelDTO.LoginRequest;
import com.irojas.demojwt.Auth.ModelDTO.RegisterRequest;
import com.irojas.demojwt.Auth.ModelDTO.RequestChangePassword;
import com.irojas.demojwt.Auth.ModelDTO.RequestChangePasswordOutOfSession;
import com.irojas.demojwt.Auth.ModelDTO.RequestChangePasswordOutOfSessionEmail;
import com.irojas.demojwt.Auth.ModelDTO.UserDTO;
import com.irojas.demojwt.Auth.ModelDTO.UserRegisterDTO;
import com.irojas.demojwt.Auth.Service.AuthService;
import com.irojas.demojwt.Auth.Service.UserService;
import com.irojas.demojwt.Jwt.JwtTokenManager;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {
    
    private final AuthService authService;
    private final JwtTokenManager jwtTokenManager;
    private UserService userService;

    
    
    public AuthController(AuthService authService, JwtTokenManager jwtTokenManager, UserService userService) {
		super();
		this.authService = authService;
		this.jwtTokenManager = jwtTokenManager;
		this.userService = userService;



	}

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse auth = authService.login(request);
        System.out.println(auth.getToken()); // Asegúrate de que el token se genera correctamente
        return ResponseEntity.ok(auth);
    }

    
    
    @PostMapping(value = "register")
    public ResponseEntity<?> registerUserAccount(@RequestBody UserRegisterDTO registerDTO) {
        // Verificar los datos de registro
        String aux = authService.checkRegisterData(registerDTO);
        
        if (!aux.equals("success")) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", aux));
        }

        // Guardar el usuario temporalmente
        TemporaryUser tempUser = authService.saveTemporaryUser(registerDTO);
        if (tempUser != null) {
            // Enviar correo de confirmación
            int emailStatus = authService.sendMessage(tempUser);
            if (emailStatus == 0) {
                return ResponseEntity.ok(Collections.singletonMap("message", "Se han guardado los datos, mire su email para confirmar el registro."));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Collections.singletonMap("error", "No se han guardado los datos, vuelva a intentar el registro."));
            }
        } else {
            // Error al guardar el usuario en la base de datos
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "No se ha conseguido añadir el usuario a la base de datos."));
        }
    }
    
    
    
    
    @GetMapping("/confirmation")
    public ResponseEntity<AuthResponse> registerConfirmation(@RequestParam("token") String token) {
        AuthResponse authResponse = authService.confirmUser(token);

        if (authResponse == null) {
            // Si no hay respuesta, devuelve un error
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(null, "Ya está registrado, por favor, inicie sesión"));
        } else if (authResponse.getToken() != null) {
        	// Si el token es válido y la operación fue exitosa
            String redirectUrl = "/auth/login"; // URL a la que quieres redirigir
            return ResponseEntity.ok(new AuthResponse(authResponse.getToken(), redirectUrl));
        } else {
            // Si hubo un error en la confirmación
        	authService.deleteTempUser(token);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse(null, "Ha habido un error, vuelva a registrarse"));
        }
    }



    
    
    @PostMapping(value = "changePassword")
    public ResponseEntity<String> changePassword(@RequestBody RequestChangePassword request, HttpServletRequest requestHeader){
    	String authorizationHeader = requestHeader.getHeader(HttpHeaders.AUTHORIZATION);
    	
    	 if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
    	        String token = authorizationHeader.substring(7); // Extrae el token sin "Bearer "
    	        
    	         
    	        if (authService.changePassword(request.getCurrentPassword(), request.getNewPassword(), token)) {
    	            return ResponseEntity.ok("Password changed successfully");
    	        }
    	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid token or password");
    	 }
    	 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: No token provided");
    }
    
    
    
    @PostMapping(value = "changePassword/change-password-out-of-session")
    public ResponseEntity<String> changePasswordOutOfSession(@RequestBody RequestChangePasswordOutOfSession request, HttpServletRequest requestHeader){
    	
    	
    	 if (authService.changePasswordOut(request.getToken(), request.getNewPassword(), request.getConfirmPassword())) {
    	        return ResponseEntity.ok("Password changed successfully");
    	 }
    	 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid token or password");
    }
  
    
    @PostMapping(value = "changePassword/send-email-to-change-password")
    public ResponseEntity<String> sendEmailTochangePasswordOutOfSession(@RequestBody RequestChangePasswordOutOfSessionEmail request, HttpServletRequest requestHeader){
    	
   	 if (authService.sendEmailTochangePasswordOut(request.getEmail())) {
   	        return ResponseEntity.ok("Email send succesfully");
   	 }
   	 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid token or password");
   }
    
    
    
    
    
    @DeleteMapping(value = "deleteUser")
    public ResponseEntity<String> deleteUser(HttpServletRequest requestHeader){
		
    	System.out.println("34534534");
    	String authorizationHeader = requestHeader.getHeader(HttpHeaders.AUTHORIZATION);
    	
	   	 if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	   	        String token = authorizationHeader.substring(7); // Extrae el token sin "Bearer "
	   	        
	   	        
	   	        
	   	        if(authService.deleteUser(token)) {
	   	        	return ResponseEntity.ok("User Deleted");
	   	        }
	   	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: No token provided");
	   	 
	   	 }
	   	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: No token provided");
    
    
    }
    
    
    @PostMapping(value = "changeUserData")
    public ResponseEntity<String> changeUserData(@RequestBody ChangeUserData userData, HttpServletRequest requestHeader) {
    	
    	String authorizationHeader = requestHeader.getHeader(HttpHeaders.AUTHORIZATION);
    	
    	if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
   	        String token = authorizationHeader.substring(7); // Extrae el token sin "Bearer "
   	        
   	        authService.changeUserData(token, userData);
   	        
   	 
   	 	}
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: No token provided");	
    }
    
    
    @GetMapping(value = "/user-data")
    public ResponseEntity<User> getUserData(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        // authHeader tendrá el formato "Bearer <token>"
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Extrae el token del encabezado
        String token = authHeader.substring(7);

        // Obtiene los datos del usuario a partir del token
        User user = jwtTokenManager.getUserFromToken(token);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build(); // Cambié a NOT_FOUND para indicar que el usuario no se encontró
    }
    
    
    
    
    
    
}  

