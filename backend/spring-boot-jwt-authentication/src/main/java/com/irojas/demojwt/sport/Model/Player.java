package com.irojas.demojwt.sport.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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
	private User User;
	
	// Relación con PlayerLeague
    @OneToMany(mappedBy = "player")
    private List<PlayerLeague> playerLeagues;
    
}
