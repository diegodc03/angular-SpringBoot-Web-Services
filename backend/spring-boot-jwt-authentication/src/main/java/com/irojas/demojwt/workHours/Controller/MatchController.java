package com.irojas.demojwt.workHours.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.irojas.demojwt.workHours.Model.Season;
import com.irojas.demojwt.workHours.Model.WorkingRoles;
import com.irojas.demojwt.workHours.ModelDTO.MatchDTO;
import com.irojas.demojwt.workHours.ModelDTO.SeasonDTO;
import com.irojas.demojwt.workHours.Service.MatchService;
import com.irojas.demojwt.workHours.Service.SeasonService;
import com.irojas.demojwt.workHours.Service.UserMatchService;
import com.irojas.demojwt.Service.UserService;


@Controller
@RequestMapping("/matches")
public class MatchController {

    private MatchService matchService;

    private SeasonService seasonService;

    private UserService userService;

    private UserMatchService userMatchService;
	
	
    // Probar
    @GetMapping("/all")
    public ResponseEntity<List<MatchDTO>> getAllMatches() {
        List<MatchDTO> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    } 
    
    //Probar
    @GetMapping("/all-of-season")
    public ResponseEntity<List<MatchDTO>> getAllMatchOfSeason(@RequestParam("season") SeasonDTO season){
    	List<MatchDTO> matches =  matchService.getAllSeasonMatches(season);
    	return ResponseEntity.ok(matches); 
    }
    
  
 // Usuario añade el rol y el cobro por el partido seleccionado (solo para partidos pasados o en la fecha actual)
    @PostMapping("/add-payment/{matchId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> addOrUpdateWorkAndPayment(
            @PathVariable Integer matchId,
            @RequestParam("role") WorkingRoles role,
            @RequestParam("payment") Double payment,
            @AuthenticationPrincipal UserDetails currentUser) {
        
        try {
            matchService.addOrUpdateWorkAndPayment(matchId, currentUser.getUsername(), role);
            return ResponseEntity.ok("Work and payment added/updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    
    
    // El admin puede añadir partidos a una temporada existente
    @PostMapping("/add-match")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addMatch(@RequestBody MatchDTO matchDTO, SeasonDTO season) {
        try {
            matchService.addMatch(matchDTO, season);
            return ResponseEntity.ok("Match added successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error adding match: " + e.getMessage());
        }
    }
    
    
    // probar
    @PostMapping("/add-season")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> addSeasonWithMatches(@RequestParam("file") MultipartFile file, SeasonDTO season){
		try {
            seasonService.addSeasonWithMatchesFromFile(file, season);
            return ResponseEntity.ok("Season and matches added successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error adding season: " + e.getMessage());
        }	
	}
	
	
	
	
}
