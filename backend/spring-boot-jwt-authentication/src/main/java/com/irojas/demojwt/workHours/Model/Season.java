package com.irojas.demojwt.workHours.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="season")
public class Season {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Basic
    @Column(nullable = false)
	private String seasonName;
	
	@OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnoreProperties(value="season")
    private List<Match> matches = new ArrayList<>();

	
	@OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	@JsonIgnoreProperties(value="season")
	private List<Money> moneyRecords = new ArrayList<>();
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	
	
	
	public Season(String seasonName, List<Match> matches) {
		super();
		this.seasonName = seasonName;
		this.matches = matches;
	}

	public Season() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
