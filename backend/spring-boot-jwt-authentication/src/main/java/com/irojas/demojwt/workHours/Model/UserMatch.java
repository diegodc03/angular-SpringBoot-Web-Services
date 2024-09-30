package com.irojas.demojwt.workHours.Model;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.irojas.demojwt.Auth.Model.User;


@Entity
@Table(name="userMatch")
public class UserMatch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="user_id")
	@JsonBackReference // Evitar serializar el user dentro de UserMatch
	private User user;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="match_id")
	@JsonBackReference // Evitar serializar el match dentro de UserMatch
	private Match match;

	private WorkingRoles workingRol;
	
	private boolean paid;
	
	
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

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
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
