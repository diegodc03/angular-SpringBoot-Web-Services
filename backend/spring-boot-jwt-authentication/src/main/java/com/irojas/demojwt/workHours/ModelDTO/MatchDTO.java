package com.irojas.demojwt.workHours.ModelDTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.irojas.demojwt.workHours.Model.Match;

public class MatchDTO {
    
    private long id;  // Identificador único del partido
    private LocalDate date;  // Fecha del partido
    private String description;  // Breve descripción o título del partido
    
    
    // Constructor vacío
    public MatchDTO() {}

    // Constructor que toma un objeto `Match` como base
    public MatchDTO(Match match) {
        this.id = match.getId();
        this.date = match.getMatchDate();
        this.description = match.getDescription();
    }

    
    public MatchDTO(String date, String description) {
		this.date = LocalDate.parse(date);
		this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
