package com.irojas.demojwt.workHours.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="match")
public class Match {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate matchDate;
	
	
	@OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    //para que no coja otra vez los valores en el JSON
    @JsonIgnoreProperties(value = "match")
	private List<UserMatch> userMatch = new ArrayList<>();
	
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "season_id")
    @JsonBackReference
	private Season season;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDate getMatchDate() {
		return matchDate;
	}


	public void setMatchDate(LocalDate matchDate) {
		this.matchDate = matchDate;
	}


	public List<UserMatch> getUserMatch() {
		return userMatch;
	}


	public void setUserMatch(List<UserMatch> userMatch) {
		this.userMatch = userMatch;
	}


	public Season getSeason() {
		return season;
	}


	public void setSeason(Season season) {
		this.season = season;
	}


	public Match(Long id, LocalDate matchDate, List<UserMatch> userMatch, Season season) {
		super();
		this.id = id;
		this.matchDate = matchDate;
		this.userMatch = userMatch;
		this.season = season;
	}


	public Match() {
		super();
	}
	
	
	
	
	
	
	
	
}
