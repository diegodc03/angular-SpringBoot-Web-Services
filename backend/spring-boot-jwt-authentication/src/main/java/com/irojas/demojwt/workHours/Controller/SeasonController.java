package com.irojas.demojwt.workHours.Controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.irojas.demojwt.workHours.ModelDTO.SeasonDTO;
import com.irojas.demojwt.workHours.Service.SeasonService;

@Controller
@RequestMapping("season")
public class SeasonController {

	
	private SeasonService seasonService;
	
	public SeasonController(SeasonService seasonService) {
		super();
		this.seasonService = seasonService;
	}

	
	@GetMapping("/all-seasons")
	public ResponseEntity<List<SeasonDTO>> getAllSeason(){
		
		try {
			List<SeasonDTO> seasonsList = this.seasonService.findAllSeasons();
			return ResponseEntity.ok(seasonsList);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	
	
	@DeleteMapping("delete-season/{id}")
	public ResponseEntity<String> deleteSeason(@PathVariable Long id){
		try {
			this.seasonService.deleteSeasonById(id);
			return ResponseEntity.ok("Season Deleted");
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	
}
