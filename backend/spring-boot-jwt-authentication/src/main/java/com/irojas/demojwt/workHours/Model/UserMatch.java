package com.irojas.demojwt.workHours.Model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.irojas.demojwt.Model.User;


@Entity
@Table(name="userMatch")
public class UserMatch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
    @JsonManagedReference
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name="match_id")
	private Match match;
	
	
	private WorkingRoles workingRol;
	

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


	public Match getMatch() {
		return match;
	}


	public void setMatch(Match match) {
		this.match = match;
	}


	public WorkingRoles getWorkingRol() {
		return workingRol;
	}


	public void setWorkingRol(WorkingRoles workingRol) {
		this.workingRol = workingRol;
	}


	public UserMatch(User user, Match match, WorkingRoles workingRol) {
		super();
		this.user = user;
		this.match = match;
		this.workingRol = workingRol;
	}


	public UserMatch() {
		super();
	}
	
	
	
	public UserMatch(User user, Match match) {
		super();
		this.user = user;
		this.match = match;
	
		
	}


	
	
	
	
	
	
}
