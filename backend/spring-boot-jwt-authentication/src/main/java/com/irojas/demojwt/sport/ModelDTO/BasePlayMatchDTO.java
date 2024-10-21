package com.irojas.demojwt.sport.ModelDTO;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.irojas.demojwt.sport.Model.League;
import com.irojas.demojwt.sport.Model.Player;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class BasePlayMatchDTO {

		
	 private Long id;
	 
	 @JsonFormat(pattern = "yyyy-MM-dd")
	 private LocalDate fecha;
	 private String ubicacion;
	 private Long leagueId;
	   
	 private String ganadorEquipo;


    
    private List<SetDTO> sets;
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Long getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}
	

	public List<SetDTO> getSets() {
		return sets;
	}

	public void setSets(List<SetDTO> sets) {
		this.sets = sets;
	}
	
	
	public String getGanadorEquipo() {
		return ganadorEquipo;
	}

	public void setGanadorEquipo(String ganadorEquipo) {
		this.ganadorEquipo = ganadorEquipo;
	}


	public BasePlayMatchDTO() {
    	super();
    }

	public BasePlayMatchDTO(Long id, LocalDate fecha, String ubicacion, Long leagueId, String ganadorEquipo,
			List<SetDTO> sets) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.leagueId = leagueId;
		this.ganadorEquipo = ganadorEquipo;
		this.sets = sets;
	}

	
	public BasePlayMatchDTO(LocalDate fecha, String ubicacion, Long leagueId, String ganadorEquipo, List<SetDTO> sets) {
		super();
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.leagueId = leagueId;
		this.ganadorEquipo = ganadorEquipo;
		this.sets = sets;
	}

	


	
	
}





