package com.irojas.demojwt.workHours.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.irojas.demojwt.Service.UserService;
import com.irojas.demojwt.workHours.ModelDTO.MatchDTO;
import com.irojas.demojwt.workHours.ModelDTO.SeasonDTO;
import com.irojas.demojwt.workHours.Service.MatchService;
import com.irojas.demojwt.workHours.Service.SeasonService;
import com.irojas.demojwt.workHours.Service.UserMatchService;

@Controller
@RequestMapping("/userMatches")
public class UserMatchController {
	
	private MatchService matchService;

    private SeasonService seasonService;

    private UserService userService;

    private UserMatchService userMatchService;
    
    
    //Se muestran todos los partidos trabajados
    
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
    
    
    
    
    
    
    
    
    
    
    
	
}
