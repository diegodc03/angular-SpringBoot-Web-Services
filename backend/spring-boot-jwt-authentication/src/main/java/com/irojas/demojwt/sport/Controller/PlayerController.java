package com.irojas.demojwt.sport.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.irojas.demojwt.Auth.Service.UserService;

@Controller
@RequestMapping("/player")
public class PlayerController {
	
	

    private UserService userService;  // Servicio que maneja la lógica de usuarios

    // Añadir usuario como jugador
    @PostMapping("/add-player")
    public ResponseEntity<String> addPlayer(
    		@AuthenticationPrincipal UserDetails currentUser) {
        try {
        	
            userService.addPlayerRole(currentUser.getUsername());
            return ResponseEntity.ok("El rol de PLAYER ha sido añadido al usuario.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Eliminar usuario como jugador
    @DeleteMapping("/delete-player")
    public ResponseEntity<String> deletePlayer(
    		@AuthenticationPrincipal UserDetails currentUser) {
        try {
            userService.removePlayerRole(currentUser.getUsername());
            return ResponseEntity.ok("El rol de PLAYER ha sido eliminado del usuario.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
    @PostMapping("/request-to-join-league")
    public ResponseEntity<String> requestToJoinLeague(){
    	
    	// Podria añadirse a una base de datos de request to join
    	
    	// enviar un mensaje al admin para añadirlo
    	
    	return null;
    }
    
    
    @DeleteMapping("get-out-of-league")
    public ResponseEntity<String> getOutOfLeague(){
    	
    	// Salirse de la la liga
    	return null;
    	
    }
    
	
	
	
}
