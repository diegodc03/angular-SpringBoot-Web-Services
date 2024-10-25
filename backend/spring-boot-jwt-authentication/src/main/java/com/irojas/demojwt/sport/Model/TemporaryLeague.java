package com.irojas.demojwt.sport.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TemporaryLeague {
	
	@Id
	Long id;
	
	String email;
	Long leagueId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}
	public TemporaryLeague(Long id, String email, Long leagueId) {
		super();
		this.id = id;
		this.email = email;
		this.leagueId = leagueId;
	}
	
	
	public TemporaryLeague( String email, Long leagueId) {
		super();
		this.email = email;
		this.leagueId = leagueId;
	}
	
	
	
	
}
