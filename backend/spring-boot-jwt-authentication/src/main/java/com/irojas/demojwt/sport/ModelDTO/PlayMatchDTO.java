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
	    
	 private PlayerDTO jugador1;
	 private PlayerDTO jugador2;
	 private PlayerDTO jugador3;
	 private PlayerDTO jugador4;
	   
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
	
	public PlayerDTO getJugador1() {
		return jugador1;
	}

	public void setJugador1(PlayerDTO jugador1) {
		this.jugador1 = jugador1;
	}

	public PlayerDTO getJugador2() {
		return jugador2;
	}

	public void setJugador2(PlayerDTO jugador2) {
		this.jugador2 = jugador2;
	}

	public PlayerDTO getJugador3() {
		return jugador3;
	}

	public void setJugador3(PlayerDTO jugador3) {
		this.jugador3 = jugador3;
	}

	public PlayerDTO getJugador4() {
		return jugador4;
	}

	public void setJugador4(PlayerDTO jugador4) {
		this.jugador4 = jugador4;
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

	public PlayMatchDTO(LocalDate fecha, String ubicacion, Long leagueId, PlayerDTO jugador1Id, PlayerDTO jugador2Id,
			PlayerDTO jugador3Id, PlayerDTO jugador4Id, String ganadorEquipo, List<SetDTO> sets) {
		super();
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.leagueId = leagueId;
		this.jugador1 = jugador1Id;
		this.jugador2 = jugador2Id;
		this.jugador3 = jugador3Id;
		this.jugador4 = jugador4Id;
		this.ganadorEquipo = ganadorEquipo;

		this.sets = sets;
	}
	
    
    
   
    public PlayMatchDTO(Long id, LocalDate fecha, String ubicacion, Long leagueId, PlayerDTO jugador1Id, PlayerDTO jugador2Id,
    		PlayerDTO jugador3Id, PlayerDTO jugador4Id, String ganadorEquipo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.leagueId = leagueId;
		this.jugador1 = jugador1Id;
		this.jugador2 = jugador2Id;
		this.jugador3 = jugador3Id;
		this.jugador4 = jugador4Id;
		this.ganadorEquipo = ganadorEquipo;

	}
    
    
    

	public PlayMatchDTO(Long id, LocalDate fecha, String ubicacion, Long leagueId, PlayerDTO jugador1Id, PlayerDTO jugador2Id,
			PlayerDTO jugador3Id, PlayerDTO jugador4Id, String ganadorEquipo, List<SetDTO> sets) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.leagueId = leagueId;
		this.jugador1 = jugador1Id;
		this.jugador2 = jugador2Id;
		this.jugador3 = jugador3Id;
		this.jugador4 = jugador4Id;
		this.ganadorEquipo = ganadorEquipo;
		this.sets = sets;
	}

	public PlayMatchDTO() {
    	super();
    }
	
	
}
