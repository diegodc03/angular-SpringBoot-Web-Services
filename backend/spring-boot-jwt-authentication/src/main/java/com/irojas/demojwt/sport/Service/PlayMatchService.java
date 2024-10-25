package com.irojas.demojwt.sport.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.irojas.demojwt.sport.Model.Equipo;
import com.irojas.demojwt.sport.Model.League;
import com.irojas.demojwt.sport.Model.Play;
import com.irojas.demojwt.sport.Model.Player;
import com.irojas.demojwt.sport.Model.PlayerLeague;
import com.irojas.demojwt.sport.Model.Set;
import com.irojas.demojwt.sport.ModelDTO.BasePlayMatchDTO;
import com.irojas.demojwt.sport.ModelDTO.PlayMatchDTOWithIds;
import com.irojas.demojwt.sport.ModelDTO.PlayMatchDTOWithPlayers;
import com.irojas.demojwt.sport.ModelDTO.PlayerDTO;
import com.irojas.demojwt.sport.ModelDTO.SetDTO;
import com.irojas.demojwt.sport.exception.ExceptionClass.UserNotFoundException;
import com.irojas.demojwt.sportRepository.LeagueRepository;
import com.irojas.demojwt.sportRepository.PlayRepository;
import com.irojas.demojwt.sportRepository.PlayerLeagueRepository;
import com.irojas.demojwt.sportRepository.PlayerRepository;

@Service
public class PlayMatchService {
	

    private PlayRepository playRepository;
    private LeagueRepository leagueRepository;
    private PlayerRepository playerRepository;
    private PlayerLeagueRepository playerLeagueRepository;
    
    public PlayMatchService (PlayRepository playRepository, LeagueRepository leagueRepository, PlayerRepository playerRepository, PlayerLeagueRepository playerLeagueRepository) {
    	this.playRepository = playRepository;
    	this.leagueRepository = leagueRepository;
    	this.playerRepository = playerRepository;
    	this.playerLeagueRepository = playerLeagueRepository;
    }
	
    
    public List<Play> getAllMatch(){
		return playRepository.findAll();
    	
    }
    
    
    public List<Play> getAllmatchesByLeague(Long leagueId){
		Optional<List<Play>> optionalMatchOfLeague = playRepository.getAllPlayByLeagueId(leagueId);
		
		if(optionalMatchOfLeague.isPresent()) {
			return optionalMatchOfLeague.get();
		}else {
			return null;
		}
    	
    }


	public void addPlayMatch(PlayMatchDTOWithIds playMatchDTO) {
		try {
            Play play = new Play();
            play.setFecha(playMatchDTO.getFecha());
            play.setUbicacion(playMatchDTO.getUbicacion());

            // Asignar liga
            Optional<League> league = leagueRepository.findById(playMatchDTO.getLeagueId());
            
            
            league.ifPresent(play::setLeague);
            /*
            if(league.isPresent()) {
            	play.setLeague(league.get());
            }*/
            
            // Asignar jugadores
            play.setJugador1(playerRepository.findById(playMatchDTO.getJugador1Id()).orElse(null));
            
            if(playMatchDTO.getJugador2Id() != null) {
            	play.setJugador2(playerRepository.findById(playMatchDTO.getJugador2Id()).orElse(null));
            }
            
            play.setJugador3(playerRepository.findById(playMatchDTO.getJugador3Id()).orElse(null));
            if(playMatchDTO.getJugador4Id() != null) {
            	play.setJugador4(playerRepository.findById(playMatchDTO.getJugador4Id()).orElse(null));
            }
            

            //play.setGanadorEquipo(playMatchDTO.getGanadorEquipo());
            int setEquipo1=0;
            int setEquipo2=0;
            
            List<Set> sets = playMatchDTO.getSets().stream()
            	    .map(setDTO -> {
            	        Set set = new Set();
            	        set.setPlay(play);  // Asocia el set con el partido correspondiente
            	        set.setNumeroSet(setDTO.getNumeroSet());
            	        set.setJuegosEquipo1(setDTO.getJuegosEquipo1());
            	        set.setJuegosEquipo2(setDTO.getJuegosEquipo2());
            	        
            	        return set;
            	    })
            	    .collect(Collectors.toList());
            int juegosEquipo1 = 0;
            int juegosEquipo2 = 0;
            
            for(Set set: sets) {
            	juegosEquipo1 = juegosEquipo1 + set.getJuegosEquipo1();
            	juegosEquipo2 = juegosEquipo2 + set.getJuegosEquipo2();
            	
            	if(set.getJuegosEquipo1() > set.getJuegosEquipo2()) {
            		setEquipo1=setEquipo1+1;
            	}else {
            		setEquipo2=setEquipo2+1;
            	}
            }
            
            if(setEquipo1 > setEquipo2) {
            	play.setGanadorEquipo(Equipo.EQUIPO1);
            }else if (setEquipo1 < setEquipo2){
            	play.setGanadorEquipo(Equipo.EQUIPO2);
            }else {
            	if (juegosEquipo1 > juegosEquipo2) {
            		play.setGanadorEquipo(Equipo.EQUIPO1);
            	}else {
            		play.setGanadorEquipo(Equipo.EQUIPO2);
            	}
            }
            
            play.setSets(sets);  // Asocia la lista de sets al partido
            
            this.updateStadisticsOfPlayers(play);
            
            //Otra forma
            /*
				List<SetDTO> setDTOs = playMatchDTO.getSets();
				List<Set> sets = new ArrayList<>();
				
				setDTOs.forEach(dto -> {
				    Set set = new Set();
				    set.setPartido(play); // Asocia el set con el partido
				    set.setNumeroSet(dto.getNumeroSet());
				    set.setJuegosEquipo1(dto.getJuegosEquipo1());
				    set.setJuegosEquipo2(dto.getJuegosEquipo2());
				    sets.add(set);
				});
				
				play.setSets(sets); // Asocia la lista de sets al partido 
            */
            
            //Actualizar valores de cada jugador para añadir a la clasificación
            
            
            
            
            playRepository.save(play);
            
        } catch (Exception e) {
        	throw new RuntimeException("Failed to add PlayMatch, the error is: ", e);
        }
		
	}

	
	public void updateStadisticsOfPlayers(Play play) {
		// Check if jugador1 and jugador2 are in the winning team
	    boolean isJugador1Victory = (play.getGanadorEquipo() == Equipo.EQUIPO1);
	    

	    this.updateStadisticsOfUnitaryPlayer(play.getJugador1(), isJugador1Victory, play.getSets(), play.getLeague(),1);
	    
	    if (play.getJugador2() != null) {
	        this.updateStadisticsOfUnitaryPlayer(play.getJugador2(), isJugador1Victory, play.getSets(), play.getLeague(),1);
	    }

	    // Both jugador3 and jugador4 belong to Equipo 2
	    this.updateStadisticsOfUnitaryPlayer(play.getJugador3(), !isJugador1Victory, play.getSets(), play.getLeague(),2);
	    
	    if (play.getJugador4() != null) {
	        this.updateStadisticsOfUnitaryPlayer(play.getJugador4(), !isJugador1Victory, play.getSets(), play.getLeague(),2);
	    }
	}
	
	
	public void updateStadisticsOfUnitaryPlayer(Player player, boolean victory, List<Set> sets, League league, int teamId) {
	    // Aquí debemos llamar a la base de datos
	    Optional<PlayerLeague> optPlayerLeague = this.playerLeagueRepository.findByPlayerAndLeague(player, league);
	    
	    if (optPlayerLeague.isPresent()) {
	        PlayerLeague playerStadistics = optPlayerLeague.get();
	        
	        // Increment total games played
	        playerStadistics.setPartidosJugados(playerStadistics.getPartidosJugados() + 1);
	        
	        // Update won/lost games based on the victory flag
	        if (victory) {
	            playerStadistics.setPartidosGanados(playerStadistics.getPartidosGanados() + 1);
	            playerStadistics.setPuntosClasificacion(playerStadistics.getPuntosClasificacion() + 1);
	        } else {
	            playerStadistics.setPartidosPerdidos(playerStadistics.getPartidosPerdidos() + 1);
	        }
	        
	        // Calculate total games and results for the current match
	        for (Set s : sets) {
	            playerStadistics.setJuegosTotales(playerStadistics.getJuegosTotales() + s.getJuegosEquipo1() + s.getJuegosEquipo2());

	         // Si el jugador pertenece al equipo 1
	            if (teamId == 1) {
	                if (victory) {
	                    // Equipo 1 ganó
	                    playerStadistics.setJuegosGanados(playerStadistics.getJuegosGanados() + s.getJuegosEquipo1());
	                    playerStadistics.setJuegosPerdidos(playerStadistics.getJuegosPerdidos() + s.getJuegosEquipo2());
	                } else {
	                    // Equipo 1 perdió
	                    playerStadistics.setJuegosGanados(playerStadistics.getJuegosGanados() + s.getJuegosEquipo1());
	                    playerStadistics.setJuegosPerdidos(playerStadistics.getJuegosPerdidos() + s.getJuegosEquipo2());
	                }
	            }

	            // Si el jugador pertenece al equipo 2
	            else if (teamId == 2) {
	                if (victory) {
	                    // Equipo 2 ganó
	                    playerStadistics.setJuegosGanados(playerStadistics.getJuegosGanados() + s.getJuegosEquipo2());
	                    playerStadistics.setJuegosPerdidos(playerStadistics.getJuegosPerdidos() + s.getJuegosEquipo1());
	                } else {
	                    // Equipo 2 perdió
	                    playerStadistics.setJuegosGanados(playerStadistics.getJuegosGanados() + s.getJuegosEquipo2());
	                    playerStadistics.setJuegosPerdidos(playerStadistics.getJuegosPerdidos() + s.getJuegosEquipo1());
	                }
	            }
	            
	        }
	        // Save updated statistics
	        playerLeagueRepository.save(playerStadistics);
	        
	    } else {
	        PlayerLeague playerStadistics = new PlayerLeague();
	        
	        playerStadistics.setLeague(league);
	        playerStadistics.setPlayer(player);
	        
	        // Initial values when player is new in the league
	        playerStadistics.setPartidosJugados(1);
	        
	        if (victory) {
	            playerStadistics.setPartidosGanados(1);
	            playerStadistics.setPuntosClasificacion(2); // Adjust according to your scoring system
	        } else {
	            playerStadistics.setPartidosPerdidos(1);
	        }
	        
	        for (Set s : sets) {
	            playerStadistics.setJuegosTotales(playerStadistics.getJuegosTotales() + s.getJuegosEquipo1() + s.getJuegosEquipo2());
	            // Si el jugador pertenece al equipo 1
	            if (teamId == 1) {
	                if (victory) {
	                    // Equipo 1 ganó
	                    playerStadistics.setJuegosGanados(playerStadistics.getJuegosGanados() + s.getJuegosEquipo1());
	                    playerStadistics.setJuegosPerdidos(playerStadistics.getJuegosPerdidos() + s.getJuegosEquipo2());
	                } else {
	                    // Equipo 1 perdió
	                    playerStadistics.setJuegosGanados(playerStadistics.getJuegosGanados() + s.getJuegosEquipo1());
	                    playerStadistics.setJuegosPerdidos(playerStadistics.getJuegosPerdidos() + s.getJuegosEquipo2());
	                }
	            }

	            // Si el jugador pertenece al equipo 2
	            else if (teamId == 2) {
	                if (victory) {
	                    // Equipo 2 ganó
	                    playerStadistics.setJuegosGanados(playerStadistics.getJuegosGanados() + s.getJuegosEquipo2());
	                    playerStadistics.setJuegosPerdidos(playerStadistics.getJuegosPerdidos() + s.getJuegosEquipo1());
	                } else {
	                    // Equipo 2 perdió
	                    playerStadistics.setJuegosGanados(playerStadistics.getJuegosGanados() + s.getJuegosEquipo2());
	                    playerStadistics.setJuegosPerdidos(playerStadistics.getJuegosPerdidos() + s.getJuegosEquipo1());
	                }
	            }
	            
	        }
	        // Save the new player statistics
	        playerLeagueRepository.save(playerStadistics);
	    }
	}

	

	public void deletePlayMatch(Long matchId) {
		Play play = playRepository.findById(matchId)
				.orElseThrow(() -> new RuntimeException("match not found"));
		
		
		List<Player> players = new ArrayList<>();
	    if (play.getJugador1() != null) players.add(play.getJugador1());
	    if (play.getJugador2() != null) players.add(play.getJugador2());
	    if (play.getJugador3() != null) players.add(play.getJugador3());
	    if (play.getJugador4() != null) players.add(play.getJugador4());
			
	 // Update player statistics for each player involved
	    int i=0;
	    if (players.size() == 2) {
	    	
	    	for(Player player: players) {
	    		if(i == 0) {
	    			updatePlayerStatistics(player, play, 1);
	    		}else {
	    			updatePlayerStatistics(player, play, 2);
	    		}
	    		i = i+1;
	    	}
	    	
	    }else if (players.size() == 4) {
	    	for(Player player: players) {
	    		if(i < 2) {
	    			updatePlayerStatistics(player, play, 1);
	    		}else {
	    			updatePlayerStatistics(player, play, 2);
	    		}
	    		i = i+1;
	    	}
	    }
		
		
		try {
			playRepository.delete(play);
		}catch(Exception e) {
			throw new RuntimeException("Failed to add PlayMatch, the error is: ", e);
		}	
	}
	
	

	private void updatePlayerStatistics(Player player, Play play, int team) {
	    // Find the corresponding PlayerLeague
	    PlayerLeague playerLeague = playerLeagueRepository.findByPlayerAndLeague(player, play.getLeague())
	            .orElseThrow(() -> new RuntimeException("PlayerLeague not found for player: " + player.getId()));
	
	    // Update the statistics as needed
	    playerLeague.setPartidosJugados(playerLeague.getPartidosJugados() - 1); // Example adjustment
	    
	    // Check if the team won or lost and update statistics accordingly
	    boolean isWinningTeam = (team == 1 && play.getGanadorEquipo() == Equipo.EQUIPO1) ||
	                            (team == 2 && play.getGanadorEquipo() == Equipo.EQUIPO2);

	    if (isWinningTeam) {
	        // If the player's team won
	        playerLeague.setPartidosGanados(playerLeague.getPartidosGanados() - 1); // Decrement wins
	        playerLeague.setPuntosClasificacion(playerLeague.getPuntosClasificacion() - 1); // Adjust classification points
	    } else {
	        // If the player's team lost
	        playerLeague.setPartidosPerdidos(playerLeague.getPartidosPerdidos() - 1); // Decrement losses
	    }
	    
	    for (Set set: play.getSets()) {
	    	
	    	if (team == 1) {
	    		playerLeague.setJuegosGanados(playerLeague.getJuegosGanados() - set.getJuegosEquipo1());
	    		playerLeague.setJuegosPerdidos(playerLeague.getJuegosPerdidos() - set.getJuegosEquipo2());
	    		playerLeague.setJuegosTotales(playerLeague.getJuegosTotales() - set.getJuegosEquipo1() - set.getJuegosEquipo2());
	    	}else {
	    		playerLeague.setJuegosGanados(playerLeague.getJuegosGanados() - set.getJuegosEquipo2());
	    		playerLeague.setJuegosPerdidos(playerLeague.getJuegosPerdidos() - set.getJuegosEquipo1());
	    		playerLeague.setJuegosTotales(playerLeague.getJuegosTotales() - set.getJuegosEquipo1() - set.getJuegosEquipo2());
	    	}	
	    }
	    
	    // Save the updated PlayerLeague
	    playerLeagueRepository.save(playerLeague);
	}
	
	
	public Play updatePlayMatch(PlayMatchDTOWithPlayers playMatchDTO) {
        Optional<Play> optionalPlay = playRepository.findById(playMatchDTO.getId());

        try {
        	if (optionalPlay.isPresent()) {
                Play play = optionalPlay.get();
                play.setFecha(playMatchDTO.getFecha());
                play.setUbicacion(playMatchDTO.getUbicacion());

                // Asignar liga
                Optional<League> league = leagueRepository.findById(playMatchDTO.getLeagueId());
                league.ifPresent(play::setLeague);

                // Asignar jugadores
                play.setJugador1(playerRepository.findById(playMatchDTO.getJugador1().getId()).orElse(null));
                play.setJugador2(playerRepository.findById(playMatchDTO.getJugador2().getId()).orElse(null));
                play.setJugador3(playerRepository.findById(playMatchDTO.getJugador3().getId()).orElse(null));
                play.setJugador4(playerRepository.findById(playMatchDTO.getJugador4().getId()).orElse(null));

                //play.setGanadorEquipo(playMatchDTO.getGanadorEquipo());

                return playRepository.save(play);
        	}
        	return null;
        }catch(Exception e) {
        	throw new RuntimeException("Failed to add PlayMatch, the error is: ", e);
        }

        
	}


	public void addPlayMatch1(BasePlayMatchDTO playMatchDTO) {
		// TODO Auto-generated method stub
		System.out.println("hola");
		
	}


	public PlayMatchDTOWithPlayers getMatchById(Long matchId) {
		Play play = this.playRepository.findMatchById(matchId)
				.orElseThrow(() -> new UserNotFoundException("User not found"));
		
		// Mapear manualmente los atributos de "play" a un nuevo objeto de tipo "PlayMatchDTOWithPlayers"
		List <SetDTO> setDTO = play.getSets().stream()
				.map(p -> new SetDTO(
						p.getId(),
						p.getNumeroSet(),
						p.getJuegosEquipo1(),
						p.getJuegosEquipo2())).collect(Collectors.toList());
				
		
		
		if(play.getJugador2()==null || play.getJugador4()==null) {
			PlayMatchDTOWithPlayers playMatchDTO = new PlayMatchDTOWithPlayers(
				    play.getId(),                          // ID del partido
				    play.getFecha(),                       // Fecha del partido
				    play.getUbicacion(),                   // Ubicación del partido
				    play.getLeague().getId(),              // ID de la liga (suponiendo que Play tiene una relación con League)
				    play.getGanadorEquipo().toString(),               // Equipo ganador
				    setDTO,                        // Sets del partido (suponiendo que ya es una lista de SetDTO)
				    new PlayerDTO(play.getJugador1().getId(), play.getJugador1().getUser().getFirstname()), // Jugador 1
				    new PlayerDTO(play.getJugador3().getId(), play.getJugador3().getUser().getFirstname()) // Jugador 3
				);
			
			// Devuelves el DTO convertido
			return playMatchDTO;
			
		}else {
			PlayMatchDTOWithPlayers playMatchDTO = new PlayMatchDTOWithPlayers(
				    play.getId(),                          // ID del partido
				    play.getFecha(),                       // Fecha del partido
				    play.getUbicacion(),                   // Ubicación del partido
				    play.getLeague().getId(),              // ID de la liga (suponiendo que Play tiene una relación con League)
				    play.getGanadorEquipo().toString(),               // Equipo ganador
				    setDTO,                        // Sets del partido (suponiendo que ya es una lista de SetDTO)
				    new PlayerDTO(play.getJugador1().getId(), play.getJugador1().getUser().getFirstname()), // Jugador 1
				    new PlayerDTO(play.getJugador2().getId(), play.getJugador2().getUser().getFirstname()),
				    new PlayerDTO(play.getJugador3().getId(), play.getJugador3().getUser().getFirstname()), // Jugador 3
				    new PlayerDTO(play.getJugador4().getId(), play.getJugador4().getUser().getFirstname())  // Jugador 4
				);
			
			// Devuelves el DTO convertido
			return playMatchDTO;
		}
		
		
		
	}
}
