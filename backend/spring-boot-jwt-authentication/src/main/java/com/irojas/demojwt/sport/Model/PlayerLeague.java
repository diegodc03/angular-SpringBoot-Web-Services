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

    @Column(name = "partidos_jugados")
    private int partidosJugados;

    @Column(name = "juegos_ganados")
    private int juegosGanados;

    @Column(name = "puntos_clasificacion")
    private int puntosClasificacion;
	
}
