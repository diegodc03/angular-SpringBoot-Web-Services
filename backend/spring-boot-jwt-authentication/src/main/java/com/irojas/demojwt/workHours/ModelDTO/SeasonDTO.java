package com.irojas.demojwt.workHours.ModelDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.irojas.demojwt.workHours.Model.Match;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

public class SeasonDTO {
	
	
	@Basic
    @Column(nullable = false)
	private String seasonName;

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public SeasonDTO(String seasonName) {
		super();
		this.seasonName = seasonName;
	}
	
	
	
	public SeasonDTO() {
	
	}
	
	
}
