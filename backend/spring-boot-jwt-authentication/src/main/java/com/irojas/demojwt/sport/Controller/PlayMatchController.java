package com.irojas.demojwt.sport.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.irojas.demojwt.sport.Model.Play;
import com.irojas.demojwt.sport.ModelDTO.PlayMatchDTO;
import com.irojas.demojwt.sport.Service.PlayMatchService;

@Controller
@RequestMapping("/sport/playMatch")
public class PlayMatchController {

	private PlayMatchService playMatchService;
	
	// Controller
	public PlayMatchController( PlayMatchService playMatchService) {
		this.playMatchService = playMatchService;
	}
	
	
	
	// Get Match
	@GetMapping("get-all-matches")
	public ResponseEntity<List<Play>> getAllMatches(
			@AuthenticationPrincipal UserDetails currentUser){
		
		List<Play> matches = this.playMatchService.getAllMatch();
		
		if(matches == null) {
			return ResponseEntity.badRequest().body(null);
		}
		return ResponseEntity.ok(matches);
	}
	
	
	@GetMapping("get-matches-by-season-id")
	public ResponseEntity<List<Play>> getAllMatches(
			@RequestBody Long leagueId,
			@AuthenticationPrincipal UserDetails currentUser){
		
		List<Play> matches = this.playMatchService.getAllmatchesByLeague(leagueId);
		
		if(matches == null) {
			return ResponseEntity.badRequest().body(null);
		}
		return ResponseEntity.ok(matches);
	}
	
	
	
	// Add match to a league
	@PostMapping("add-play-match")
	public ResponseEntity<String> addPlayMatch(
			@AuthenticationPrincipal UserDetails currentUser,
			@RequestBody PlayMatchDTO playMatchDTO){
			
		try {
			playMatchService.addPlayMatch(playMatchDTO);
           return ResponseEntity.ok("Season and matches added successfully.");
       } catch (Exception e) {
           return ResponseEntity.badRequest().body("Error adding season: " + e.getMessage());
       }	
	}
	
	
	
	@DeleteMapping("delete-play-match")
	public ResponseEntity<String> deleteMatchOfLeague(
			@AuthenticationPrincipal UserDetails currentUser,
			@RequestBody Long matchId){
		
		try {
			playMatchService.deletePlayMatch(matchId);
           return ResponseEntity.ok("Season and matches added successfully.");
       } catch (Exception e) {
           return ResponseEntity.badRequest().body("Error adding season: " + e.getMessage());
       }
	}
	
	
	
	// Actualizar un partido existente
    @PutMapping("update-match")
    public ResponseEntity<Play> updatePlayMatch(
            @AuthenticationPrincipal UserDetails currentUser,
            @RequestBody PlayMatchDTO playMatchDTO) {

       try {
			return ResponseEntity.ok(playMatchService.updatePlayMatch(playMatchDTO));
      } catch (Exception e) {
          return ResponseEntity.badRequest().body(null);
      }
    }
    
    
    
   
		
	
	
	
	

	
	
	
	
	
	
	
}
