package com.irojas.demojwt.workHours.Controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.irojas.demojwt.Auth.Service.UserService;
import com.irojas.demojwt.workHours.Model.WorkingRoles;
import com.irojas.demojwt.workHours.ModelDTO.MatchDTO;
import com.irojas.demojwt.workHours.ModelDTO.MatchWithUserInfoDTO;
import com.irojas.demojwt.workHours.ModelDTO.RoleRequest;
import com.irojas.demojwt.workHours.ModelDTO.SeasonDTO;
import com.irojas.demojwt.workHours.Service.MatchService;
import com.irojas.demojwt.workHours.Service.SeasonService;
import com.irojas.demojwt.workHours.Service.UserMatchService;

@Controller
@RequestMapping("/work-hours/userMatches")
public class UserMatchController {
	
	private MatchService matchService;

    private SeasonService seasonService;

    private UserService userService;

    private UserMatchService userMatchService;
    
    
    
    public UserMatchController(MatchService matchService, SeasonService seasonService, UserService userService,
			UserMatchService userMatchService) {
		super();
		this.matchService = matchService;
		this.seasonService = seasonService;
		this.userService = userService;
		this.userMatchService = userMatchService;
	}

	//Se muestran todos los partidos trabajados, pero hay tambien que mostrar los no trabajados
    
    // Probar
    @GetMapping("/all")
    public ResponseEntity<List<MatchDTO>> getAllMatches() {
        List<MatchDTO> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    } 
    

// OK
    @GetMapping("/get-all-matches-of-season-with-user-info/{seasonId}")
    public ResponseEntity<List<MatchWithUserInfoDTO>> getAllMatchesOfSeasonWithUserInfo(
            @PathVariable Integer seasonId,
            @AuthenticationPrincipal UserDetails currentUser) {

            try {
                List<MatchWithUserInfoDTO> matches = matchService.getAllMatchesOfSeasonWithUserInfo(seasonId, currentUser.getUsername());
                return ResponseEntity.ok(matches);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
     }
    
    
// OK
    @PostMapping("/add-payment/{matchId}")
    public ResponseEntity<String> addOrUpdateWorkAndPayment(
            @PathVariable Long matchId,
            @RequestBody RoleRequest roleRequest,
            @AuthenticationPrincipal UserDetails currentUser
            ) {

        try {
        	WorkingRoles role = WorkingRoles.valueOf(roleRequest.getRole().toUpperCase());
        	
            userMatchService.addOrUpdateWorkAndPayment(matchId, currentUser.getUsername(), role);
            return ResponseEntity.ok("Work and payment added/updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
// OK
    @DeleteMapping("/delete-worked-match/{matchId}")
    public ResponseEntity<String> deleteWorkPayment(
    		@PathVariable Long matchId,
            @AuthenticationPrincipal UserDetails currentUser){
    	
    	try {
            userMatchService.deleteMatchWork(matchId, currentUser.getUsername());
            return ResponseEntity.ok("Work and payment added/updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    	
    	
    }
    


}
