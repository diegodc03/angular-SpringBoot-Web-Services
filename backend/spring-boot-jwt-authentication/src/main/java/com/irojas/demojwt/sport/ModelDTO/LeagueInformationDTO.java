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

public class LeagueInformationDTO extends LeagueDTO{



	private List<PlayMatchDTOWithPlayers> matchPlayed = new ArrayList<>();
	private List<PlayerLeagueDTO> playerLeagues = new ArrayList<>();
	

	
	public List<PlayMatchDTOWithPlayers> getMatchPlayed() {
		return matchPlayed;
	}
	public void setMatchPlayed(List<PlayMatchDTOWithPlayers> matchPlayed) {
		this.matchPlayed = matchPlayed;
	}
	public List<PlayerLeagueDTO> getPlayerLeagues() {
		return playerLeagues;
	}
	public void setPlayerLeagues(List<PlayerLeagueDTO> playerLeagues) {
		this.playerLeagues = playerLeagues;
	}
	
	
	
	public LeagueInformationDTO(Long id, String name, boolean requestToJoinLeague, List<PlayMatchDTOWithPlayers> matchPlayed,
			List<PlayerLeagueDTO> playerLeagues) {
		super(id, name, requestToJoinLeague);
		this.matchPlayed = matchPlayed;
		this.playerLeagues = playerLeagues;
	}
	
	
	
	
	
	
	
	
}
