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
import org.springframework.web.bind.annotation.RequestParam;

import com.irojas.demojwt.Auth.Service.UserService;
import com.irojas.demojwt.sport.Model.Player;
import com.irojas.demojwt.sport.ModelDTO.LeagueIdDTO;
import com.irojas.demojwt.sport.ModelDTO.PlayerDTO;
import com.irojas.demojwt.sport.Service.PlayerService;

@Controller
@RequestMapping("/sport/player")
public class PlayerController {
	
	

    private UserService userService;  // Servicio que maneja la lógica de usuarios
    private PlayerService playerService;
    
    
    public PlayerController(UserService userService, PlayerService playerService) {
		super();
		this.userService = userService;
		this.playerService = playerService;
	}

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
    
    
    
  //request-to-join
    @PostMapping("/request-to-join-league")
    public ResponseEntity<String> requestToJoinLeague(
    		@AuthenticationPrincipal UserDetails currentUser){
    	
    	// Podria añadirse a una base de datos de request to join
    	
    	// enviar un mensaje al admin para añadirlo
    	
    	return null;
    }
    
    
    @PostMapping("/join-league")
    public ResponseEntity<String> joinLeague(
    		@AuthenticationPrincipal UserDetails currentUser,
    		@RequestBody LeagueIdDTO leagueId){
		
    	this.playerService.joinLeague(currentUser.getUsername(), leagueId.getLeagueId());
    	return ResponseEntity.ok("Successfully joined the league.");
    	
    }
    
    
    //Get-out-of-league
    @DeleteMapping("get-out-of-league")
    public ResponseEntity<String> getOutOfLeague(
    		@AuthenticationPrincipal UserDetails currentUser,
    		@RequestBody LeagueIdDTO deletePlayer){
    	
    	
    	this.playerService.deletePlayerOfLeague(deletePlayer.getLeagueId(), currentUser.getUsername());
    	return ResponseEntity.ok("Player removed from league successfully.");
    
    	
    }
    
    
    @GetMapping("/get-all-players/{leagueId}")
    public ResponseEntity<List<PlayerDTO>> getAllPlayersOfLeagues(
    		@AuthenticationPrincipal UserDetails currentUser,
    		@PathVariable Long leagueId){
    	
    	List<PlayerDTO> playerDTOList = playerService.getAllPlayers(leagueId);
    	 
    	return ResponseEntity.ok(playerDTOList);
    }
    
    
    
	
	
	
}
