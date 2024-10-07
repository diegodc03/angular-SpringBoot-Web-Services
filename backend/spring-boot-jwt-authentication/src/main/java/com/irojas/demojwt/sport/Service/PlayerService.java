package com.irojas.demojwt.sport.Service;

import org.springframework.stereotype.Service;

import com.irojas.demojwt.sportRepository.PlayerRepository;

@Service
public class PlayerService {

	
	private PlayerRepository playerRepository;
		
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	
	public String deletePlayerOfLeague(Long leagueId, String email) {
		
		
		
		return null;
	}
	
	
}
