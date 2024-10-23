package com.irojas.demojwt.sport.ModelDTO;

import java.time.LocalDate;
import java.util.List;

import com.irojas.demojwt.sport.Model.Equipo;
import com.irojas.demojwt.sport.Model.Set;




public class PlayMatchDTOWithPlayers extends BasePlayMatchDTO {

	private PlayerDTO jugador1;
    private PlayerDTO jugador2;
    private PlayerDTO jugador3;
    private PlayerDTO jugador4;

    // Getters y setters para PlayerDTO
    public PlayerDTO getJugador1() { return jugador1; }
    public void setJugador1(PlayerDTO jugador1) { this.jugador1 = jugador1; }

    public PlayerDTO getJugador2() { return jugador2; }
    public void setJugador2(PlayerDTO jugador2) { this.jugador2 = jugador2; }

    public PlayerDTO getJugador3() { return jugador3; }
    public void setJugador3(PlayerDTO jugador3) { this.jugador3 = jugador3; }

    public PlayerDTO getJugador4() { return jugador4; }
    public void setJugador4(PlayerDTO jugador4) { this.jugador4 = jugador4; }
    
    
	
  
	public PlayMatchDTOWithPlayers(LocalDate fecha, String ubicacion, Long leagueId, String ganadorEquipo, List<SetDTO> sets,PlayerDTO jugador1, PlayerDTO jugador2, PlayerDTO jugador3, PlayerDTO jugador4) {
		super(fecha, ubicacion, leagueId, ganadorEquipo, sets);
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.jugador3 = jugador3;
		this.jugador4 = jugador4;
	}
    
    public PlayMatchDTOWithPlayers(Long id, LocalDate fecha, String ubicacion, Long leagueId, String ganadorEquipo, List<SetDTO> sets,PlayerDTO jugador1, PlayerDTO jugador2, PlayerDTO jugador3, PlayerDTO jugador4) {
		super(id, fecha, ubicacion, leagueId, ganadorEquipo, sets);
		
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.jugador3 = jugador3;
		this.jugador4 = jugador4;
	}
    
  
	public PlayMatchDTOWithPlayers() {
		super();
	}
	
}

