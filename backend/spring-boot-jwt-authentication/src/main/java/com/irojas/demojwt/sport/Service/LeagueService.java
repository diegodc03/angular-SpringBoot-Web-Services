package com.irojas.demojwt.sport.Service;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.Repository.UserRepository;
import com.irojas.demojwt.sport.Model.League;
import com.irojas.demojwt.sport.Model.Player;
import com.irojas.demojwt.sport.Model.PlayerLeague;
import com.irojas.demojwt.sport.Model.Set;
import com.irojas.demojwt.sport.ModelDTO.LeagueDTO;
import com.irojas.demojwt.sport.ModelDTO.PlayMatchDTO;
import com.irojas.demojwt.sport.ModelDTO.PlayerLeagueDTO;
import com.irojas.demojwt.sport.ModelDTO.LeagueInformationDTO;
import com.irojas.demojwt.sport.ModelDTO.SetDTO;
import com.irojas.demojwt.sportRepository.LeagueRepository;
import com.irojas.demojwt.sportRepository.PlayerLeagueRepository;
import com.irojas.demojwt.sportRepository.PlayerRepository;


@Service
public class LeagueService {
	
	private LeagueRepository leagueRepository;
	private PlayerRepository playerRepository;
	private UserRepository userRepository;
	private PlayerLeagueRepository playerLeagueRepository;
	
	private LeagueService(LeagueRepository leagueRepository, 
						PlayerRepository playerRepository, 
						UserRepository userRepository, 
						PlayerLeagueRepository playerLeagueRepository) {
		this.leagueRepository = leagueRepository;
		this.playerRepository = playerRepository;
		this.userRepository = userRepository;
		this.playerLeagueRepository = playerLeagueRepository;
	}
	
	

    public void createLeague(LeagueDTO leagueDTO) {
        try {
            League league = new League();
            league.setName(leagueDTO.getName());
            league.setRequireRequest(leagueDTO.isRequireRequest());            
            
            leagueRepository.save(league);
      
        } catch (Exception e) {
        	throw new RuntimeException("Failed to create the league ", e);
        }
    }

    public void deleteLeague(Long leagueId) {
        Optional<League> optionalLeague = leagueRepository.findById(leagueId);

        if (optionalLeague.isPresent()) {
        	try {
        		leagueRepository.delete(optionalLeague.get());
        	}catch(Exception e) {
        		throw new RuntimeException("Failed to delete the league ", e);
        	}
        } 
    }
	
    
	public String getOutOfPlayerOfLeague(Long playerId) {
		
		
		
		return null;
		
	}



	public List<LeagueDTO> getAllLeagues( String string) {
		// TODO Auto-generated method stub
		try {
			List<League> leagues = leagueRepository.findAll();
			
			if(leagues != null) {
				return leagues.stream()
						.map(l -> 
						new LeagueDTO(l.getId(), l.getName(), l.isRequireRequest())).collect(Collectors.toList());
						
			}
			return null;
			
		}catch(Exception e){
			throw new RuntimeException("Failed to get all leagues the league ", e);
		}
	}



	public List<LeagueDTO> getLeaguesJoined(String email) {
		// TODO Auto-generated method stub
		try {
			User user = userRepository.findByEmail(email)
					.orElseThrow(() -> new RuntimeException("Player not found"));
		
	        // Busca el jugador a partir del username.
	        Player player = playerRepository.findByUserId(user.getId())
	            .orElseThrow(() -> new RuntimeException("Player not found"));

	        // Busca las relaciones de PlayerLeague para este jugador.
	        List<PlayerLeague> playerLeagues = playerLeagueRepository.findByPlayerId(player.getId());

	        // Convierte cada PlayerLeague en un LeagueDTO
	        return playerLeagues.stream()
	            .map(playerLeague -> {
	                League league = playerLeague.getLeague();
	                return new LeagueDTO(
	                    league.getId(),
	                    league.getName(),
	                    league.isRequireRequest()
	                );
	            })
	            .collect(Collectors.toList());

	    } catch (Exception e) {
	        throw new RuntimeException("Failed to get leagues joined by the user", e);
	    }
	}



	public LeagueInformationDTO getLeagueInfo(Long leagueId) {
		// TODO Auto-generated method stub
		try {
			 League league = this.leagueRepository.findById(leagueId)
					 .orElseThrow(() -> new RuntimeException("league not found"));
			
			 // Convertir lista de partidos a MatchDTO
		        List<PlayMatchDTO> matches = league.getMatchPlayed().stream()
		                .map(play -> new PlayMatchDTO(
		                        play.getId(),
		                        play.getFecha(),
		                        play.getUbicacion(),
		                        play.getLeague().getId(),
		                        play.getJugador1().getId(),
		                        play.getJugador2().getId(),
		                        play.getJugador3() != null ? play.getJugador3().getId() : null,
		                        play.getJugador4() != null ? play.getJugador4().getId() : null,
		                        play.getGanadorEquipo().toString(),
		                        play.getSets().stream()
		                                .map(set -> new SetDTO(
		                                        set.getId(),
		                                        set.getNumeroSet(),
		                                        set.getJuegosEquipo1(),
		                                        set.getJuegosEquipo2()
		                                ))
		                                .collect(Collectors.toList())
		                ))
		                .collect(Collectors.toList());

		        // Convertir lista de PlayerLeague a PlayerLeaguesdtoModule
		        List<PlayerLeagueDTO> playerLeagues = league.getPlayerLeagues().stream()
		                .map(playerLeague -> new PlayerLeagueDTO(
		                        playerLeague.getId(),
		                        playerLeague.getPlayer().getId(),
		                        playerLeague.getLeague().getId(),
		                        playerLeague.getPartidosGanados(),
		                        playerLeague.getPartidosPerdidos(),
		                        playerLeague.getPartidosJugados(),
		                        playerLeague.getJuegosGanados(),
		                        playerLeague.getJuegosPerdidos(),
		                        playerLeague.getJuegosTotales(),
		                        playerLeague.getPuntosClasificacion()
		                ))
		                .collect(Collectors.toList());

		        // Crear el LeagueInformationDTO con toda la información
		        return new LeagueInformationDTO(
		                league.getId(),
		                league.getName(),
		                league.isRequireRequest(),
		                matches,
		                playerLeagues
		        );
			
			
		}catch(Exception e){
			throw new RuntimeException("Failed to getLeagueInfo the league ", e);
		}
	
	}
	
	
	
}
