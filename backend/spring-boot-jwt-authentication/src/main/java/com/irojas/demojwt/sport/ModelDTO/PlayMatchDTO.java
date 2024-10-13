package com.irojas.demojwt.sport.ModelDTO;

import java.time.LocalDate;
import java.util.List;

import com.irojas.demojwt.sport.Model.League;
import com.irojas.demojwt.sport.Model.Player;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PlayMatchDTO {

		
	private Long id;

    private LocalDate fecha;

    private String ubicacion;
    
    private Long leagueId;

    private Long jugador1Id;

    private Long jugador2Id;

    private Long jugador3Id;

    private Long jugador4Id;
    
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

	public Long getJugador1Id() {
		return jugador1Id;
	}

	public void setJugador1Id(Long jugador1Id) {
		this.jugador1Id = jugador1Id;
	}

	public Long getJugador2Id() {
		return jugador2Id;
	}

	public void setJugador2Id(Long jugador2Id) {
		this.jugador2Id = jugador2Id;
	}

	public Long getJugador3Id() {
		return jugador3Id;
	}

	public void setJugador3Id(Long jugador3Id) {
		this.jugador3Id = jugador3Id;
	}

	public Long getJugador4Id() {
		return jugador4Id;
	}

	public void setJugador4Id(Long jugador4Id) {
		this.jugador4Id = jugador4Id;
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

	public PlayMatchDTO(LocalDate fecha, String ubicacion, Long leagueId, Long jugador1Id, Long jugador2Id,
			Long jugador3Id, Long jugador4Id, String ganadorEquipo, List<SetDTO> sets) {
		super();
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.leagueId = leagueId;
		this.jugador1Id = jugador1Id;
		this.jugador2Id = jugador2Id;
		this.jugador3Id = jugador3Id;
		this.jugador4Id = jugador4Id;
		this.ganadorEquipo = ganadorEquipo;

		this.sets = sets;
	}
	
    
    
   
    public PlayMatchDTO(Long id, LocalDate fecha, String ubicacion, Long leagueId, Long jugador1Id, Long jugador2Id,
			Long jugador3Id, Long jugador4Id, String ganadorEquipo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.leagueId = leagueId;
		this.jugador1Id = jugador1Id;
		this.jugador2Id = jugador2Id;
		this.jugador3Id = jugador3Id;
		this.jugador4Id = jugador4Id;
		this.ganadorEquipo = ganadorEquipo;

	}
    
    
    

	public PlayMatchDTO(Long id, LocalDate fecha, String ubicacion, Long leagueId, Long jugador1Id, Long jugador2Id,
			Long jugador3Id, Long jugador4Id, String ganadorEquipo, List<SetDTO> sets) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.leagueId = leagueId;
		this.jugador1Id = jugador1Id;
		this.jugador2Id = jugador2Id;
		this.jugador3Id = jugador3Id;
		this.jugador4Id = jugador4Id;
		this.ganadorEquipo = ganadorEquipo;
		this.sets = sets;
	}

	public PlayMatchDTO() {
    	super();
    }
	
	
}
