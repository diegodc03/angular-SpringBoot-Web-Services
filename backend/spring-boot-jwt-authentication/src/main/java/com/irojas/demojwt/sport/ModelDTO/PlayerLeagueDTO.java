package com.irojas.demojwt.sport.ModelDTO;

import com.irojas.demojwt.sport.Model.League;
import com.irojas.demojwt.sport.Model.Player;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PlayerLeagueDTO {

    private Long id;
    private Long player;
	private Long league;
	private int partidosGanados;
    private int partidosPerdidos;
    private int partidosJugados;
    private int juegosGanados;
    private int juegosPerdidos;
    private int juegosTotales;
    private int puntosClasificacion;
	
    
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPlayer() {
		return player;
	}
	public void setPlayer(Long player) {
		this.player = player;
	}
	public Long getLeague() {
		return league;
	}
	public void setLeague(long leagueId) {
		this.league = leagueId;
	}
	public int getPartidosGanados() {
		return partidosGanados;
	}
	public void setPartidosGanados(int partidosGanados) {
		this.partidosGanados = partidosGanados;
	}
	public int getPartidosPerdidos() {
		return partidosPerdidos;
	}
	public void setPartidosPerdidos(int partidosPerdidos) {
		this.partidosPerdidos = partidosPerdidos;
	}
	public int getPartidosJugados() {
		return partidosJugados;
	}
	public void setPartidosJugados(int partidosJugados) {
		this.partidosJugados = partidosJugados;
	}
	public int getJuegosGanados() {
		return juegosGanados;
	}
	public void setJuegosGanados(int juegosGanados) {
		this.juegosGanados = juegosGanados;
	}
	public int getJuegosPerdidos() {
		return juegosPerdidos;
	}
	public void setJuegosPerdidos(int juegosPerdidos) {
		this.juegosPerdidos = juegosPerdidos;
	}
	public int getJuegosTotales() {
		return juegosTotales;
	}
	public void setJuegosTotales(int juegosTotales) {
		this.juegosTotales = juegosTotales;
	}
	public int getPuntosClasificacion() {
		return puntosClasificacion;
	}
	public void setPuntosClasificacion(int puntosClasificacion) {
		this.puntosClasificacion = puntosClasificacion;
	}
	
	
	public PlayerLeagueDTO(Long id, Long playerId, Long leagueId, int partidosGanados, int partidosPerdidos,
			int partidosJugados, int juegosGanados, int juegosPerdidos, int juegosTotales, int puntosClasificacion) {
		super();
		this.id = id;
		this.player = playerId;
		this.league = leagueId;
		this.partidosGanados = partidosGanados;
		this.partidosPerdidos = partidosPerdidos;
		this.partidosJugados = partidosJugados;
		this.juegosGanados = juegosGanados;
		this.juegosPerdidos = juegosPerdidos;
		this.juegosTotales = juegosTotales;
		this.puntosClasificacion = puntosClasificacion;
	}

	
    
	
	
}
