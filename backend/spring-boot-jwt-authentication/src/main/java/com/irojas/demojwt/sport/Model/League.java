package com.irojas.demojwt.sport.Model;





import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="league")
public class League {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
    private String name;
	
	private LeagueType leagueType;
	
	// Nueva columna para determinar si se requiere una solicitud para unirse
    @Column(name = "require_request", nullable = false)
    private boolean requireRequest;
	
	@OneToMany(mappedBy = "league",  fetch = FetchType.EAGER)
	private List<Play> matchPlayed = new ArrayList<>();;
	
	// Relación con PlayerLeague
    @OneToMany(mappedBy = "league",  fetch = FetchType.EAGER)
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
	
	public boolean isRequireRequest() {
		return requireRequest;
	}

	public void setRequireRequest(boolean requireRequest) {
		this.requireRequest = requireRequest;
	}
    
	public LeagueType getLeagueType() {
		return leagueType;
	}

	public void setLeagueType(LeagueType leagueType) {
		this.leagueType = leagueType;
	}

	
	
	public League(Long id, String name,  LeagueType leagueType, boolean requireRequest, List<Play> matchPlayed,
			List<PlayerLeague> playerLeagues) {
		super();
		this.id = id;
		this.name = name;
		this.leagueType = leagueType;
		this.requireRequest = requireRequest;
		this.matchPlayed = matchPlayed;
		this.playerLeagues = playerLeagues;
	}

	

	public League( String name, LeagueType leagueType, boolean requireRequest, List<Play> matchPlayed,
			List<PlayerLeague> playerLeagues) {
		super();

		this.name = name;
		this.leagueType = leagueType;
		this.requireRequest = requireRequest;
		this.matchPlayed = matchPlayed;
		this.playerLeagues = playerLeagues;
	}
	
	
	public League() {
		super();
	}
	
	
	
	
}
