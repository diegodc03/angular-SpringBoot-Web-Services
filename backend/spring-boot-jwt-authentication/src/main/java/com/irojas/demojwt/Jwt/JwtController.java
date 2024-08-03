package com.irojas.demojwt.Jwt;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(value = "/api/v1/token")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class JwtController {

    private JwtService jwtService;
    
        
    public JwtController( JwtService jwtService) {
		super();
		this.jwtService = jwtService;
	}
    
    
    @GetMapping(value = "/validate-token")
    public ResponseEntity<String> checkToken(HttpServletRequest request){
    	String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    	
    	 if (authorizationHeader  == null) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token found");
         }
    	 
    	 if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
             String token = authorizationHeader.substring(7); // Extraer el token
             
             try {
         		// El token debe estar en el formato "Bearer token"
                 
                 if (jwtService.isTokenValid(token)) {
                 	// correct
                	 
                     return ResponseEntity.ok("Token is valid");
                 } else {
                     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
                 }
         	}catch(Exception e){
         		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error validating token");
         	} 
         }else {
        	 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token found");
         }
    	 
    		
    }
	
	
	
}
