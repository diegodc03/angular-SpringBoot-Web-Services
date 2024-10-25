package com.irojas.demojwt.sport.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import com.irojas.demojwt.Auth.Model.User;




@Entity
@Table(name="player")
public class Player {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="user_id", referencedColumnName="id",nullable=false)
	private User user;
	
	// Relación con PlayerLeague
	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PlayerLeague> playerLeagues = new ArrayList<>();



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<PlayerLeague> getPlayerLeagues() {
		return playerLeagues;
	}

	public void setPlayerLeagues(List<PlayerLeague> playerLeagues) {
		this.playerLeagues = playerLeagues;
	}

	
	
	
	public Player(User user, List<PlayerLeague> playerLeagues) {
		super();
		this.user = user;
		this.playerLeagues = playerLeagues;
	}

	public Player() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
    
    
    
    
    
    
    
}
