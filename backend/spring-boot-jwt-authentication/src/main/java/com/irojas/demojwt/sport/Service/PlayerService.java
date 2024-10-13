package com.irojas.demojwt.sport.Service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.Repository.UserRepository;
import com.irojas.demojwt.sport.Model.League;
import com.irojas.demojwt.sport.Model.Player;
import com.irojas.demojwt.sport.Model.PlayerLeague;
import com.irojas.demojwt.sport.exception.ExceptionClass.LeagueNotFoundException;
import com.irojas.demojwt.sport.exception.ExceptionClass.PlayerAlreadyInLeagueException;
import com.irojas.demojwt.sport.exception.ExceptionClass.PlayerNotFoundException;
import com.irojas.demojwt.sport.exception.ExceptionClass.UserNotFoundException;
import com.irojas.demojwt.sportRepository.LeagueRepository;
import com.irojas.demojwt.sportRepository.PlayerLeagueRepository;
import com.irojas.demojwt.sportRepository.PlayerRepository;

@Service
public class PlayerService {

	private UserRepository userRepository;
	private PlayerRepository playerRepository;
	private LeagueRepository leagueRepository;
	private PlayerLeagueRepository playerLeagueRepository;
	

	public PlayerService(UserRepository userRepository, 
			PlayerRepository playerRepository,
			LeagueRepository leagueRepository,
			PlayerLeagueRepository playerLeagueRepository) {
		super();
		this.userRepository = userRepository;
		this.playerRepository = playerRepository;
		this.leagueRepository = leagueRepository;
		this.playerLeagueRepository = playerLeagueRepository;
	}


	public String deletePlayerOfLeague(Long leagueId, String email) {
		
		
		
		return null;
	}


	
	
	// Tengo que poner un tipo de error para que devuelva ese tipo y no uno tipico
	public void joinLeague(String username, Long leagueId) {
	    User user = userRepository.findByEmail(username)
	            .orElseThrow(() -> new UserNotFoundException("User not found"));
	    
	    Player player = playerRepository.findByUserId(user.getId())
	            .orElseThrow(() -> new PlayerNotFoundException("Player not found"));
	    
	    League league = leagueRepository.findById(leagueId)
	            .orElseThrow(() -> new LeagueNotFoundException("League not found"));
	    
	    Optional<PlayerLeague> existingPlayerLeague = playerLeagueRepository.findByPlayerAndLeague(player, league);
	    
	    if (existingPlayerLeague.isPresent()) {
	        throw new PlayerAlreadyInLeagueException("Player is already part of this league");
	    }
	    
	    PlayerLeague playerLeague = new PlayerLeague(player, league);
	    player.getPlayerLeagues().add(playerLeague);
	    
	    playerRepository.save(player);
	   
	}
	
	
}
