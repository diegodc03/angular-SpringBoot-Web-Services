package com.irojas.demojwt.sport.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TemporaryLeague {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	String email;
	Long leagueId;
	String token;
	
	
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public TemporaryLeague( String email, Long leagueId, String token) {
		super();
		this.email = email;
		this.leagueId = leagueId;
		this.token = token;
	}
	
	
	public TemporaryLeague(Long id, String email, Long leagueId, String token) {
		super();
		this.id = id;
		this.email = email;
		this.leagueId = leagueId;
		this.token = token;
	}
	public TemporaryLeague() {
		super();
	}
	
	
	
	
}
