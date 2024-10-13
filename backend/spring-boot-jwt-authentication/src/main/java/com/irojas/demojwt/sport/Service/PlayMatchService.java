package com.irojas.demojwt.sport.Service;

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
import com.irojas.demojwt.sport.ModelDTO.PlayMatchDTO;
import com.irojas.demojwt.sport.ModelDTO.SetDTO;
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


	public void addPlayMatch(PlayMatchDTO playMatchDTO) {
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
            play.setJugador2(playerRepository.findById(playMatchDTO.getJugador2Id()).orElse(null));
            play.setJugador3(playerRepository.findById(playMatchDTO.getJugador3Id()).orElse(null));
            play.setJugador4(playerRepository.findById(playMatchDTO.getJugador4Id()).orElse(null));

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

            for(Set set: sets) {
            	
            	if(set.getJuegosEquipo1() > set.getJuegosEquipo2()) {
            		setEquipo1=setEquipo1+1;
            	}else {
            		setEquipo2=setEquipo2+1;
            	}
            }
            
            if(setEquipo1 > setEquipo2) {
            	play.setGanadorEquipo(Equipo.EQUIPO1);
            }else {
            	play.setGanadorEquipo(Equipo.EQUIPO2);
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
		this.updateStadisticsOfUnitaryPlayer(play.getJugador1(), true, play.getSets(), play.getLeague());
		this.updateStadisticsOfUnitaryPlayer(play.getJugador2(), true, play.getSets(), play.getLeague());
		this.updateStadisticsOfUnitaryPlayer(play.getJugador3(), false, play.getSets(), play.getLeague());
		this.updateStadisticsOfUnitaryPlayer(play.getJugador4(), false, play.getSets(), play.getLeague());
	}
	
	
	public void updateStadisticsOfUnitaryPlayer(Player player, boolean victory, List<Set> sets, League league) {
		
		// Aqui debemos llamar a la base de datos
		Optional<PlayerLeague> optPlayerLeague = this.playerLeagueRepository.findByPlayerAndLeague(player, league);
		
		if(optPlayerLeague.isPresent()) {
			
			PlayerLeague playerStadistics = optPlayerLeague.get();
			
			playerStadistics.setPartidosJugados(playerStadistics.getPartidosJugados() + 1);
			
			if(victory) {
				playerStadistics.setPartidosGanados(playerStadistics.getPartidosGanados() + 1);
				playerStadistics.setPuntosClasificacion(playerStadistics.getPuntosClasificacion() + 2);
			}else {
				playerStadistics.setPartidosPerdidos(playerStadistics.getPartidosPerdidos() + 1);
			}
			
			for(Set s: sets) {
				playerStadistics.setJuegosTotales(playerStadistics.getJuegosTotales() + s.getJuegosEquipo1() + s.getJuegosEquipo2());
				if(victory) {
					playerStadistics.setJuegosGanados(playerStadistics.getJuegosGanados() + s.getJuegosEquipo1());
					playerStadistics.setJuegosPerdidos(playerStadistics.getJuegosPerdidos() + s.getJuegosEquipo2());
				}else {
					playerStadistics.setJuegosGanados(playerStadistics.getJuegosGanados() + s.getJuegosEquipo2());
					playerStadistics.setJuegosPerdidos(playerStadistics.getJuegosPerdidos() + s.getJuegosEquipo1());
				}		
			}
			
			
			playerLeagueRepository.save(playerStadistics);			
		}else {
			PlayerLeague playerStadistics = new PlayerLeague();
			
			playerStadistics.setLeague(league);
			playerStadistics.setPlayer(player);
			
			playerStadistics.setPartidosJugados(1);
			
			if(victory) {
				playerStadistics.setPartidosGanados(1);
				playerStadistics.setPuntosClasificacion(2);
			}else {
				playerStadistics.setPartidosPerdidos(1);
			}
			
			for(Set s: sets) {
				playerStadistics.setJuegosTotales(playerStadistics.getJuegosTotales() + s.getJuegosEquipo1() + s.getJuegosEquipo2());
				if(victory) {
					playerStadistics.setJuegosGanados(playerStadistics.getJuegosGanados() + s.getJuegosEquipo1());
					playerStadistics.setJuegosPerdidos(playerStadistics.getJuegosPerdidos() + s.getJuegosEquipo2());
				}else {
					playerStadistics.setJuegosGanados(playerStadistics.getJuegosGanados() + s.getJuegosEquipo2());
					playerStadistics.setJuegosPerdidos(playerStadistics.getJuegosPerdidos() + s.getJuegosEquipo1());
				}		
			}
		}
	}
	
	

	public void deletePlayMatch(Long matchId) {
		
		Optional<Play> optionalPlay = playRepository.findById(matchId);

		try {
			playRepository.delete(optionalPlay.get());
		}catch(Exception e) {
			throw new RuntimeException("Failed to add PlayMatch, the error is: ", e);
		}	
	}
	
	
	public Play updatePlayMatch(PlayMatchDTO playMatchDTO) {
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
                play.setJugador1(playerRepository.findById(playMatchDTO.getJugador1Id()).orElse(null));
                play.setJugador2(playerRepository.findById(playMatchDTO.getJugador2Id()).orElse(null));
                play.setJugador3(playerRepository.findById(playMatchDTO.getJugador3Id()).orElse(null));
                play.setJugador4(playerRepository.findById(playMatchDTO.getJugador4Id()).orElse(null));

                //play.setGanadorEquipo(playMatchDTO.getGanadorEquipo());

                return playRepository.save(play);
        	}
        	return null;
        }catch(Exception e) {
        	throw new RuntimeException("Failed to add PlayMatch, the error is: ", e);
        }

        
	}
}
