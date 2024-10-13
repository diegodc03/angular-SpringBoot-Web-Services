package com.irojas.demojwt.sport.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.irojas.demojwt.sport.ModelDTO.LeagueDTO;
import com.irojas.demojwt.sport.ModelDTO.LeagueInformationDTO;
import com.irojas.demojwt.sport.Service.LeagueService;

@Controller
@RequestMapping("/sport/league")
public class LeagueController {
	
	
	// Añadir liga, es decir una nueva clasificacion
	private LeagueService leagueService;
	
	private LeagueController(LeagueService leagueService) {
		this.leagueService = leagueService;
	}

	
	// Get League
	@GetMapping("/get-all-leagues")
	public ResponseEntity<List<LeagueDTO>> getAllLeagues(
            @AuthenticationPrincipal UserDetails currentUser) {


        try {
        	List<LeagueDTO> leagues = leagueService.getAllLeagues(currentUser.getUsername());
        	return ResponseEntity.ok(leagues);
        }catch(Exception e) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
	
	
	@GetMapping("/get-leagues-joined")
	public ResponseEntity<List<LeagueDTO>> getLeaguesJoined(
            @AuthenticationPrincipal UserDetails currentUser) {

        try {
        	List<LeagueDTO> leaguesJoined = leagueService.getLeaguesJoined(currentUser.getUsername());
        	return ResponseEntity.ok(leaguesJoined);
        }catch(Exception e) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
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
    
    
    @GetMapping("/get-league-information/{leagueId}")
    public ResponseEntity<LeagueInformationDTO> getLeagueInfo(
    		@AuthenticationPrincipal UserDetails currentUser,
    		@PathVariable Long leagueId){
    	
    	try {
    		LeagueInformationDTO leagueInformationDTO = leagueService.getLeagueInfo(leagueId);
        	return ResponseEntity.ok(leagueInformationDTO);
        }catch(Exception e) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    
    
    }
    
    
    
	
	
	
	
	
	
	
	
	
}
