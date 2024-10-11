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

public class PlayerLeagueDTO {


	private Long id;
    private String name;
	private boolean requireRequest;
	private List<Play> matchPlayed = new ArrayList<>();
	private List<PlayerLeague> playerLeagues = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isRequireRequest() {
		return requireRequest;
	}
	public void setRequireRequest(boolean requireRequest) {
		this.requireRequest = requireRequest;
	}
	public List<Play> getMatchPlayed() {
		return matchPlayed;
	}
	public void setMatchPlayed(List<Play> matchPlayed) {
		this.matchPlayed = matchPlayed;
	}
	public List<PlayerLeague> getPlayerLeagues() {
		return playerLeagues;
	}
	public void setPlayerLeagues(List<PlayerLeague> playerLeagues) {
		this.playerLeagues = playerLeagues;
	}
	
	
	
	public PlayerLeagueDTO(Long id, String name, boolean requireRequest, List<Play> matchPlayed,
			List<PlayerLeague> playerLeagues) {
		super();
		this.id = id;
		this.name = name;
		this.requireRequest = requireRequest;
		this.matchPlayed = matchPlayed;
		this.playerLeagues = playerLeagues;
	}
	
	
	
	
	
}
