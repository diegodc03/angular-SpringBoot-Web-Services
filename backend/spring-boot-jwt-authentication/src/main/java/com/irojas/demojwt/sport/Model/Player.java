package com.irojas.demojwt.sport.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.irojas.demojwt.Auth.Model.User;

@Entity
@Table(name="player")
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="user_id", referencedColumnName="id",nullable=false)
	private User User;
	
	@Column(name = "partidos_ganados")
    private int partidosGanados;

    @Column(name = "partidos_jugados")
    private int partidosJugados;

    @Column(name = "juegos_ganados")
    private int juegosGanados;

    @Column(name = "puntos_clasificacion")
    private int puntosClasificacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
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

	
	
	public Player(User user, int partidosGanados, int partidosJugados, int juegosGanados,
			int puntosClasificacion) {
		super();
		User = user;
		this.partidosGanados = partidosGanados;
		this.partidosJugados = partidosJugados;
		this.juegosGanados = juegosGanados;
		this.puntosClasificacion = puntosClasificacion;
	}

	public Player() {
		super();
	}
    
    
    
}
