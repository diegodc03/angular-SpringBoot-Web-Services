package com.irojas.demojwt.workHours.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="sport_match")
public class Match implements Comparable<Match>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate matchDate;
	
	private String localTeam;
	
	private String awayTeam;
	
	private String description;
	
	@OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    //para que no coja otra vez los valores en el JSON
    @JsonIgnoreProperties(value = "match")
	private List<UserMatch> userMatch = new ArrayList<>();
	
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "season_id")
    @JsonBackReference
	private Season season;
	
	private boolean is_local;


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
	
	public String getDescription() {
		return description;
	}


	public void setDescription() {
		this.description = (this.localTeam.toUpperCase() +" VS. "+ this.awayTeam.toUpperCase());
	}

	
	public boolean isIs_local() {
		return is_local;
	}


	public void setIs_local(boolean is_local) {
		this.is_local = is_local;
	}


	public String getLocalTeam() {
		return localTeam;
	}


	public void setLocalTeam(String localTeam) {
		this.localTeam = localTeam;
	}


	public String getAwayTeam() {
		return awayTeam;
	}


	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}


	public Match(Long id, LocalDate matchDate, List<UserMatch> userMatch, Season season, String description) {
		super();
		this.id = id;
		this.matchDate = matchDate;
		this.userMatch = userMatch;
		this.season = season;
		this.description = description;
	}
	
	


	public Match() {
		super();
	}


	@Override
	public int compareTo(Match o) {
		return this.matchDate.compareTo(o.matchDate);
		
	}


	
	
	
	
	
	
	
	
}
