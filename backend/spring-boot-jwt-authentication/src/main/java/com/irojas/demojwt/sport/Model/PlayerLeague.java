package com.irojas.demojwt.sport.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "player_league")
public class PlayerLeague {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
	
    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    private League league;

    @Column(name = "partidos_ganados")
    private int partidosGanados;
    
    @Column(name = "partidos_perdidos")
    private int partidosPerdidos;

    @Column(name = "partidos_jugados")
    private int partidosJugados;

    @Column(name = "juegos_ganados")
    private int juegosGanados;
    
    @Column(name = "juegos_perdidos")
    private int juegosPerdidos;
    
    @Column(name = "juegos_totales")
    private int juegosTotales;

    @Column(name = "puntos_clasificacion")
    private int puntosClasificacion;

    
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public int getPartidosGanados() {
		return partidosGanados;
	}

	public void setPartidosGanados(int partidosGanados) {
		this.partidosGanados = partidosGanados;
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

	public int getPuntosClasificacion() {
		return puntosClasificacion;
	}

	public void setPuntosClasificacion(int puntosClasificacion) {
		this.puntosClasificacion = puntosClasificacion;
	}

	public int getPartidosPerdidos() {
		return partidosPerdidos;
	}

	public void setPartidosPerdidos(int partidosPerdidos) {
		this.partidosPerdidos = partidosPerdidos;
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

	

	public PlayerLeague(Player player, League league, int partidosGanados, int partidosPerdidos, int partidosJugados,
			int juegosGanados, int juegosPerdidos, int juegosTotales, int puntosClasificacion) {
		super();
		this.player = player;
		this.league = league;
		this.partidosGanados = partidosGanados;
		this.partidosPerdidos = partidosPerdidos;
		this.partidosJugados = partidosJugados;
		this.juegosGanados = juegosGanados;
		this.juegosPerdidos = juegosPerdidos;
		this.juegosTotales = juegosTotales;
		this.puntosClasificacion = puntosClasificacion;
	}

	public PlayerLeague() {
		super();
		this.juegosGanados = 0;
		this.juegosPerdidos = 0;
		this.juegosTotales = 0;
		
		this.partidosGanados = 0;
		this.partidosJugados = 0;
		this.partidosPerdidos = 0;
		
		this.puntosClasificacion = 0;
	}
	


}
