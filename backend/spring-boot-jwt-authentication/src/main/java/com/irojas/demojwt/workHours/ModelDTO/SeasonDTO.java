package com.irojas.demojwt.workHours.ModelDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.irojas.demojwt.workHours.Model.Match;
import com.irojas.demojwt.workHours.Model.Season;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

public class SeasonDTO {
	
	private Long id;
	
	
	@Basic
    @Column(nullable = false)
	private String seasonName;

	public String getSeasonName() {
		return seasonName;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	
	
	public SeasonDTO(Long id, String seasonName) {
		super();
		this.id = id;
		this.seasonName = seasonName;
	}
	
	
	public SeasonDTO(Season season) {
		this.id = season.getId();
		this.seasonName = season.getSeasonName();
	}
	
	
	public SeasonDTO() {
	
	}
	
	
}
