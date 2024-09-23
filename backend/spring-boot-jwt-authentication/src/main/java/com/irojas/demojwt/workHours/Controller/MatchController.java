package com.irojas.demojwt.workHours.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.irojas.demojwt.Auth.Service.UserService;
import com.irojas.demojwt.workHours.Model.Season;
import com.irojas.demojwt.workHours.Model.WorkingRoles;
import com.irojas.demojwt.workHours.ModelDTO.MatchDTO;
import com.irojas.demojwt.workHours.ModelDTO.SeasonDTO;
import com.irojas.demojwt.workHours.Service.MatchService;
import com.irojas.demojwt.workHours.Service.SeasonService;
import com.irojas.demojwt.workHours.Service.UserMatchService;


@Controller
@RequestMapping("/work-hours/matches")
public class MatchController {

    private MatchService matchService;

    private SeasonService seasonService;

    private UserService userService;

    private UserMatchService userMatchService;
    
    
    
	
	
    public MatchController(MatchService matchService, SeasonService seasonService, UserService userService,
			UserMatchService userMatchService) {
		super();
		this.matchService = matchService;
		this.seasonService = seasonService;
		this.userService = userService;
		this.userMatchService = userMatchService;
	}
    
    
// OK
    @GetMapping("/all")
    public ResponseEntity<List<MatchDTO>> getAllMatches() {
        List<MatchDTO> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    } 
    
// OK
    @GetMapping("/all-of-season")
    public ResponseEntity<List<MatchDTO>> getAllMatchOfSeason(@RequestParam("seasonId") Long id){
    	List<MatchDTO> matches =  matchService.getAllSeasonMatches(id);
    	return ResponseEntity.ok(matches); 
    }
    
  

    
//OK --> ONLY IT WILL PUT ADMIN AUTHORIZE YET

    @PostMapping("/add-match")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addMatch(@RequestBody MatchDTO matchDTO) {
        try {
            matchService.addMatch(matchDTO);
            return ResponseEntity.ok("Match added successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error adding match: " + e.getMessage());
        }
    }
    
    
    @DeleteMapping("/delete-match/{matchId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMatch(@PathVariable Long matchId) {
        try {
            matchService.deleteMatch(matchId);
            return ResponseEntity.ok("Match added successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error adding match: " + e.getMessage());
        }
    }
    
    
    
 //OK --> ONLY IT WILL PUT ADMIN AUTHORIZE YET
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
