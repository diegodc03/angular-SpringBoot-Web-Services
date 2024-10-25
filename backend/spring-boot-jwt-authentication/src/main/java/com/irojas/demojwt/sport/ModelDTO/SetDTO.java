package com.irojas.demojwt.sport.ModelDTO;



public class SetDTO {
	

    private Long id;

    private int numeroSet;

    private int juegosEquipo1;

    private int juegosEquipo2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	
	
	
	public SetDTO(int numeroSet, int juegosEquipo1, int juegosEquipo2) {
		super();
		this.numeroSet = numeroSet;
		this.juegosEquipo1 = juegosEquipo1;
		this.juegosEquipo2 = juegosEquipo2;
	}
	
	

	
	public SetDTO(Long id, int numeroSet, int juegosEquipo1, int juegosEquipo2) {
		super();
		this.id = id;
		this.numeroSet = numeroSet;
		this.juegosEquipo1 = juegosEquipo1;
		this.juegosEquipo2 = juegosEquipo2;
	}

	public SetDTO() {
		super();
	}
    
    
	
	
	
	
	
}
