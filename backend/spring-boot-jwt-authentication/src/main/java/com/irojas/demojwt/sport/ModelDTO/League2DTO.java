package com.irojas.demojwt.sport.ModelDTO;

import java.util.ArrayList;
import java.util.List;

import com.irojas.demojwt.sport.Model.Play;
import com.irojas.demojwt.sport.Model.PlayerLeague;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class League2DTO extends LeagueDTO{



	private List<PlayMatchDTO> matchPlayed = new ArrayList<>();
	private List<PlayerLeagueDTO> playerLeagues = new ArrayList<>();
	

	
	public List<PlayMatchDTO> getMatchPlayed() {
		return matchPlayed;
	}
	public void setMatchPlayed(List<PlayMatchDTO> matchPlayed) {
		this.matchPlayed = matchPlayed;
	}
	public List<PlayerLeagueDTO> getPlayerLeagues() {
		return playerLeagues;
	}
	public void setPlayerLeagues(List<PlayerLeagueDTO> playerLeagues) {
		this.playerLeagues = playerLeagues;
	}
	
	
	
	public League2DTO(Long id, String name, boolean requireRequest, List<PlayMatchDTO> matchPlayed,
			List<PlayerLeagueDTO> playerLeagues) {
		super(id, name, requireRequest);
		this.matchPlayed = matchPlayed;
		this.playerLeagues = playerLeagues;
	}
	
	
	
	
	
	
	
	
}
