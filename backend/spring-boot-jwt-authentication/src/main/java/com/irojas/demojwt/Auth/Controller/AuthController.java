package com.irojas.demojwt.Auth.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.ModelDTO.AuthResponse;
import com.irojas.demojwt.Auth.ModelDTO.ChangeUserData;
import com.irojas.demojwt.Auth.ModelDTO.LoginRequest;
import com.irojas.demojwt.Auth.ModelDTO.RegisterRequest;
import com.irojas.demojwt.Auth.ModelDTO.RequestChangePassword;
import com.irojas.demojwt.Auth.ModelDTO.UserDTO;
import com.irojas.demojwt.Auth.Service.AuthService;
import com.irojas.demojwt.Jwt.JwtTokenManager;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {
    
    private final AuthService authService;
    private final JwtTokenManager jwtTokenManager;

    
    
    public AuthController(AuthService authService, JwtTokenManager jwtTokenManager) {
		super();
		this.authService = authService;
		this.jwtTokenManager = jwtTokenManager;



	}

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse auth = authService.login(request);
        System.out.println(auth.getToken()); // Asegúrate de que el token se genera correctamente
        return ResponseEntity.ok(auth);
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
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

