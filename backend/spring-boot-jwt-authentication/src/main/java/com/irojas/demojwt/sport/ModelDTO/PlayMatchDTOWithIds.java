package com.irojas.demojwt.sport.ModelDTO;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PlayMatchDTOWithIds extends BasePlayMatchDTO{
	
	@JsonProperty("jugador1Id")
    private Long jugador1Id;

    @JsonProperty("jugador2Id")
    private Long jugador2Id;

    @JsonProperty("jugador3Id")
    private Long jugador3Id;

    @JsonProperty("jugador4Id")
    private Long jugador4Id;

	    // Getters y setters para los IDs
	    public Long getJugador1Id() { return jugador1Id; }
	    public void setJugador1Id(Long jugador1Id) { this.jugador1Id = jugador1Id; }

	    public Long getJugador2Id() { return jugador2Id; }
	    public void setJugador2Id(Long jugador2Id) { this.jugador2Id = jugador2Id; }

	    public Long getJugador3Id() { return jugador3Id; }
	    public void setJugador3Id(Long jugador3Id) { this.jugador3Id = jugador3Id; }

	    public Long getJugador4Id() { return jugador4Id; }
	    public void setJugador4Id(Long jugador4Id) { this.jugador4Id = jugador4Id; }
		
	    
	    
	    public PlayMatchDTOWithIds(LocalDate fecha, String ubicacion, Long leagueId, String ganadorEquipo, List<SetDTO> sets, Long jugador1Id, Long jugador2Id, Long jugador3Id, Long jugador4Id) {
	    	super(fecha, ubicacion, leagueId, ganadorEquipo, sets);
			this.jugador1Id = jugador1Id;
			this.jugador2Id = jugador2Id;
			this.jugador3Id = jugador3Id;
			this.jugador4Id = jugador4Id;
		}
	
	    public PlayMatchDTOWithIds(Long id, LocalDate fecha, String ubicacion, Long leagueId, String ganadorEquipo, List<SetDTO> sets, Long jugador1Id, Long jugador2Id, Long jugador3Id, Long jugador4Id) {
	    	super(id, fecha, ubicacion, leagueId, ganadorEquipo, sets);
			this.jugador1Id = jugador1Id;
			this.jugador2Id = jugador2Id;
			this.jugador3Id = jugador3Id;
			this.jugador4Id = jugador4Id;
		}
	    
	    public PlayMatchDTOWithIds() {
	        super();
	    }

	    
	
	
	
}
