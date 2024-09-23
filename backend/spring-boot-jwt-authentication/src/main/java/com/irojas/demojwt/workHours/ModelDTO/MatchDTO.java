package com.irojas.demojwt.workHours.ModelDTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.irojas.demojwt.workHours.Model.Match;

public class MatchDTO {
    
    private long id;  // Identificador único del partido
    private LocalDate date;  // Fecha del partido
    private String localTeam;
    private String awayTeam;
    private String description;
    private Long seasonId;
    
    // Constructor vacío
    public MatchDTO() {}

    // Constructor que toma un objeto `Match` como base
    public MatchDTO(Match match) {
        this.id = match.getId();
        this.date = match.getMatchDate();
        this.description = match.getDescription();
    }
    
    public MatchDTO(String id) {
    	this.id = Long.parseLong(id);
    }

    
    public MatchDTO(String date, String localTeam, String awayTeam, String seasonId) {
		this.date = LocalDate.parse(date);
		this.localTeam = localTeam;
		this.awayTeam = awayTeam;
		this.seasonId = Long.parseLong(seasonId);
		
		
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
	}
	
	

    
}
