package com.irojas.demojwt.sport.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.irojas.demojwt.sport.ModelDTO.LeagueDTO;
import com.irojas.demojwt.sport.Service.LeagueService;

public class LeagueController {
	
	
	// Añadir liga, es decir una nueva clasificacion
	private LeagueService leagueService;
	
	private LeagueController(LeagueService leagueService) {
		this.leagueService = leagueService;
	}

	//Create League
	@PostMapping("/create-league")
    public ResponseEntity<String> createLeague(
            @AuthenticationPrincipal UserDetails currentUser,
            @RequestBody LeagueDTO leagueDTO) {


        try {
        	leagueService.createLeague(leagueDTO);
        	return ResponseEntity.ok("La liga ha sido creada");
        }catch(Exception e) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
    
    //Create League
    @PostMapping("/delete-league")
    public ResponseEntity<String> deleteLeague(
    		@AuthenticationPrincipal UserDetails currentUser,
    		@RequestBody Long leagueId){
    	
    	try {
    		leagueService.deleteLeague(leagueId);
        	return ResponseEntity.ok("La liga ha sido eliminada");
        }catch(Exception e) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
    
    
    
	
	
	
	
	
	
	
	
	
}
