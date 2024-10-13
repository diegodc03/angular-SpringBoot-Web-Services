package com.irojas.demojwt.sport.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="play")
public class Play {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "ubicacion")
    private String ubicacion;
    
    @ManyToOne
    @JoinColumn(name = "league", referencedColumnName = "id")
    private League league;

    @ManyToOne
    @JoinColumn(name = "jugador1_id", referencedColumnName = "id")
    private Player jugador1;

    @ManyToOne
    @JoinColumn(name = "jugador2_id", referencedColumnName = "id")
    private Player jugador2;

    @ManyToOne
    @JoinColumn(name = "jugador3_id", referencedColumnName = "id")
    private Player jugador3;

    @ManyToOne
    @JoinColumn(name = "jugador4_id", referencedColumnName = "id")
    private Player jugador4;

    @Column(name = "ganador_equipo")
    private Equipo ganadorEquipo;

    @OneToMany(mappedBy = "play", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Set> sets = new ArrayList<>();
    
    
    
    
    
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

	public Player getJugador1() {
		return jugador1;
	}

	public void setJugador1(Player jugador1) {
		this.jugador1 = jugador1;
	}

	public Player getJugador2() {
		return jugador2;
	}

	public void setJugador2(Player jugador2) {
		this.jugador2 = jugador2;
	}

	public Player getJugador3() {
		return jugador3;
	}

	public void setJugador3(Player jugador3) {
		this.jugador3 = jugador3;
	}

	public Player getJugador4() {
		return jugador4;
	}

	public void setJugador4(Player jugador4) {
		this.jugador4 = jugador4;
	}

	public Equipo getGanadorEquipo() {
		return ganadorEquipo;
	}

	public void setGanadorEquipo(Equipo ganadorEquipo) {
		this.ganadorEquipo = ganadorEquipo;
	}
	
	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}
	

	public List<Set> getSets() {
		return sets;
	}

	public void setSets(List<Set> sets) {
		this.sets = sets;
	}

	public Play(LocalDate fecha, String ubicacion, Player jugador1, Player jugador2, Player jugador3, Player jugador4,
			Equipo ganadorEquipo, List<Set> sets) {
		super();
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.jugador3 = jugador3;
		this.jugador4 = jugador4;
		this.ganadorEquipo = ganadorEquipo;
		this.sets = sets;
	}
	
	
	public Play() {
		super();
	}
	
    
    
    
}
