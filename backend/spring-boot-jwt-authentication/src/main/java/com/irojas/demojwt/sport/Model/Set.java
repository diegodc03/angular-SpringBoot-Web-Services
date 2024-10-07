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
@Table(name="game_set")  // Cambiamos el nombre a game_set para evitar conflictos con palabras reservadas
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "play_id", referencedColumnName = "id")
    private Play play;

    @Column(name = "numero_set")
    private int numeroSet;

    @Column(name = "juegos_equipo1")
    private int juegosEquipo1;

    @Column(name = "juegos_equipo2")
    private int juegosEquipo2;
    
    
    
    // Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Play getPartido() {
		return play;
	}

	public void setPartido(Play play) {
		this.play = play;
	}

	public int getNumeroSet() {
		return numeroSet;
	}

	public void setNumeroSet(int numeroSet) {
		this.numeroSet = numeroSet;
	}

	public int getJuegosEquipo1() {
		return juegosEquipo1;
	}

	public void setJuegosEquipo1(int juegosEquipo1) {
		this.juegosEquipo1 = juegosEquipo1;
	}

	public int getJuegosEquipo2() {
		return juegosEquipo2;
	}

	public void setJuegosEquipo2(int juegosEquipo2) {
		this.juegosEquipo2 = juegosEquipo2;
	}

	
	
    
    // Constructor
	public Set(Play partido, int numeroSet, int juegosEquipo1, int juegosEquipo2) {
		super();
		this.play = partido;
		this.numeroSet = numeroSet;
		this.juegosEquipo1 = juegosEquipo1;
		this.juegosEquipo2 = juegosEquipo2;
	}
	
	public Set() {
		super();
	}
    
    
    
}
