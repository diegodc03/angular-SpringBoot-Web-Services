package com.irojas.demojwt.workHours.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.Repository.UserRepository;
import com.irojas.demojwt.workHours.ModelDTO.MatchWithUserInfoDTO;
import com.irojas.demojwt.workHours.Model.Match;
import com.irojas.demojwt.workHours.Model.Season;
import com.irojas.demojwt.workHours.Model.Team;
import com.irojas.demojwt.workHours.Model.UserMatch;
import com.irojas.demojwt.workHours.Model.WorkingRoles;
import com.irojas.demojwt.workHours.ModelDTO.MatchDTO;
import com.irojas.demojwt.workHours.ModelDTO.SeasonDTO;
import com.irojas.demojwt.workHours.ModelDTO.WorkedMatchWithUserInfo;
import com.irojas.demojwt.workHours.Repository.MatchRepository;
import com.irojas.demojwt.workHours.Repository.SeasonRepository;
import com.irojas.demojwt.workHours.Repository.TeamRepository;
import com.irojas.demojwt.workHours.Repository.UserMatchRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class MatchService {


    private MatchRepository matchRepository;

    private UserRepository userRepository;

    private UserMatchRepository userMatchRepository;

    private SeasonRepository seasonRepository;
    
    private TeamRepository teamRepository;
    
    
    
    public MatchService(MatchRepository matchRepository, UserRepository userRepository,
			UserMatchRepository userMatchRepository, SeasonRepository seasonRepository, TeamRepository teamRepository) {
		super();
		this.matchRepository = matchRepository;
		this.userRepository = userRepository;
		this.userMatchRepository = userMatchRepository;
		this.seasonRepository = seasonRepository;
		this.teamRepository = teamRepository;
	}

    
// OK
    // Obtener todos los partidos de la temporada (usuarios pueden ver todos)
    public List<MatchDTO> getAllMatches() {
        List<Match> matches = matchRepository.findAll();
        return matches.stream()
                .map(MatchDTO::new)
                .collect(Collectors.toList());
    }
    
// OK
    public List<MatchDTO> getAllSeasonMatches(Long seasonId){
    	
    	Optional<Season> optSeason = seasonRepository.findById(seasonId);
    	if(!optSeason.isPresent()) {
    		return null;
    	}
    	
    	return optSeason.get().getMatches().stream().map(MatchDTO::new).collect(Collectors.toList());
    
    }
    
     
    

// OK
    // Añadir un nuevo partido a la temporada (solo admin)
    public Match addMatch(MatchDTO matchDTO) throws Exception {
    	
    	Team localTeam = new Team(matchDTO.getLocalTeam().toUpperCase());
        Team awayTeam = new Team(matchDTO.getAwayTeam().toUpperCase());

        // Verificar si el equipo local ya existe
        Team existingLocalTeam = teamRepository.findByTeamName(localTeam.getTeamName());
        if (existingLocalTeam == null) {
            teamRepository.save(localTeam);
        } else {
            localTeam = existingLocalTeam; // Usar el equipo existente
        }

        // Verificar si el equipo visitante ya existe
        Team existingAwayTeam = teamRepository.findByTeamName(awayTeam.getTeamName());
        if (existingAwayTeam == null) {
            teamRepository.save(awayTeam);
        } else {
            awayTeam = existingAwayTeam; // Usar el equipo existente
        }
    	
    	// check if the season already exists
    	Optional<Season> existanceSeason = seasonRepository.findById(matchDTO.getSeasonId());
		if(existanceSeason == null || !existanceSeason.stream().anyMatch(s -> s.getId().equals(matchDTO.getSeasonId())) || !existanceSeason.isPresent()) {
			throw new Exception("Season not exists: " + matchDTO.getSeasonId());
		}
		
		Season season = existanceSeason.get();
		
		// check if the match alredy exist
		/*
		Optional<Match> optMatch = this.matchRepository.findByMatchDate(matchDTO.getDate());
		if(optMatch.isPresent()) {
			throw new Exception("The Date of the match you want to add is another match: " + matchDTO.getDescription() + " --> " + matchDTO.getDate());
		} 
		*/
		
        Match match = new Match();
        match.setMatchDate(matchDTO.getDate());
        match.setSeason(season);
        match.setLocalTeam(matchDTO.getLocalTeam());
        match.setAwayTeam(matchDTO.getAwayTeam());
        match.setDescription();
        
        
        if(match.getDescription().contains("CB ZAMORA VS.")) {
        	match.setIs_local(true);
        }
        
        return matchRepository.save(match);        
    }

   

    public List<MatchWithUserInfoDTO> getMatchesWithWorkedMatches(List<Match> matches, User user){
    	
    	
    	// Lista para almacenar los resultados
        List<MatchWithUserInfoDTO> result = new ArrayList<>();
    	
    	//Recorrer los partidos de la temporada
        for (Match match : matches) {
            
            // Verificar si el usuario trabajó en este partido usando el repositorio de UserMatch
            Optional<UserMatch> userMatchOpt = userMatchRepository.findByUserAndMatch(user, match);
            
            if (userMatchOpt.isPresent()) {
                // Si el usuario trabajó en el partido, añadimos los detalles del trabajo
            	WorkedMatchWithUserInfo workedMatchWitchUserInfo = new WorkedMatchWithUserInfo();
                UserMatch userMatch = userMatchOpt.get();
                workedMatchWitchUserInfo.setMatch(convertToDTO(match));
                workedMatchWitchUserInfo.setUserWorked(true);
                workedMatchWitchUserInfo.setRole(userMatch.getWorkingRol());
                workedMatchWitchUserInfo.setPayment(userMatch.getWorkingRol().getSalary());
                
                result.add(workedMatchWitchUserInfo);
                
            } else {
            	MatchWithUserInfoDTO matchWithUserInfo = new MatchWithUserInfoDTO();
                matchWithUserInfo.setMatch(convertToDTO(match));  // Convertimos el partido a DTO
                // Si no trabajó, sólo indicamos que no trabajó en este partido
                matchWithUserInfo.setUserWorked(false);
             // Añadimos el objeto a la lista de resultados
                result.add(matchWithUserInfo);
            }
        }
        return result;
    	
    }
    
    
    
	public List<MatchWithUserInfoDTO> getAllMatchesOfSeasonWithUserInfo(Integer seasonId, String email) {
		// TODO Auto-generated method stub
		List<Match> matches = matchRepository.findBySeasonIdOrderedByDate(seasonId);
		
		
		// Buscar el usuario autenticado por su username
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        
        List<MatchWithUserInfoDTO> results = this.getMatchesWithWorkedMatches(matches, user);
        
        return results;
	}
	
	
	// Método para convertir Match a MatchDTO
    private MatchDTO convertToDTO(Match match) {
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setId(match.getId());
        matchDTO.setDate(match.getMatchDate());
        matchDTO.setDescription(match.getDescription());        // Añadir otros campos relevantes de "Match"
        matchDTO.setAwayTeam(match.getAwayTeam());
        matchDTO.setLocalTeam(match.getLocalTeam());
        matchDTO.setSeasonId(match.getSeason().getId());
        return matchDTO;
    }


	public void deleteMatch(Long id) {
		
		if (id == null) {
	        throw new IllegalArgumentException("Invalid match data. MatchDTO or Match ID is null.");
	    }
		
		try {
	        // Verificar si el partido existe antes de eliminarlo
	        Optional<Match> matchOptional = matchRepository.findById(id);
	        System.out.println(matchOptional.get().getLocalTeam());
	        Match match = matchOptional.get();
	        matchRepository.delete(match);
	        
	    } catch (Exception e) {
	        // Registrar el error para propósitos de depuración
	        System.err.println("Error deleting match: " + e.getMessage());
	        throw new RuntimeException("Failed to delete match with ID: " + id, e);
	    }
	}


	public List<MatchWithUserInfoDTO> getLocalMatches(Long seasonId, String email) {
		
		List<Match> matches = this.matchRepository.findMatchesBySeasonAndIsLocalTrueOrderedByDate(seasonId);
		
		// Buscar el usuario autenticado por su username
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        
        List<MatchWithUserInfoDTO> results = this.getMatchesWithWorkedMatches(matches, user);
        
        return results;
	}

	public List<MatchWithUserInfoDTO> getAwayMatches(Long seasonId, String email) {
		
		List<Match> matches = this.matchRepository.findMatchesBySeasonAndIsLocalFalseOrderedByDate(seasonId);
		
		// Buscar el usuario autenticado por su username
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        
        List<MatchWithUserInfoDTO> results = this.getMatchesWithWorkedMatches(matches, user);
        
        return results;
	}
	


	public List<WorkedMatchWithUserInfo> getWorkedMatches(Long seasonId, String email) {
		
		List<Match> matches = this.matchRepository.findMatchesBySeasonAndIsLocalTrueOrderedByDate(seasonId);
		
		// Buscar el usuario autenticado por su username
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
		
     // Lista para almacenar los resultados
        List<WorkedMatchWithUserInfo> result = new ArrayList<>();
    	
    	//Recorrer los partidos de la temporada
        for (Match match : matches) {
            
            // Verificar si el usuario trabajó en este partido usando el repositorio de UserMatch
            Optional<UserMatch> userMatchOpt = userMatchRepository.findByUserAndMatch(user, match);
            
            if (userMatchOpt.isPresent()) {
                // Si el usuario trabajó en el partido, añadimos los detalles del trabajo
            	WorkedMatchWithUserInfo workedMatchWitchUserInfo = new WorkedMatchWithUserInfo();
                UserMatch userMatch = userMatchOpt.get();
                workedMatchWitchUserInfo.setMatch(convertToDTO(match));
                workedMatchWitchUserInfo.setUserWorked(true);
                workedMatchWitchUserInfo.setRole(userMatch.getWorkingRol());
                workedMatchWitchUserInfo.setPayment(userMatch.getWorkingRol().getSalary());
                
                result.add(workedMatchWitchUserInfo);
                
            }
        }
        return result;
		


	}


	public List<MatchWithUserInfoDTO> getNotWorkedMatches(Long seasonId, String email) {
		
		List<Match> matches = this.matchRepository.findMatchesBySeasonAndIsLocalTrueOrderedByDate(seasonId);
		
		// Buscar el usuario autenticado por su username
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
		
     // Lista para almacenar los resultados
        List<MatchWithUserInfoDTO> result = new ArrayList<>();
    	
    	//Recorrer los partidos de la temporada
        for (Match match : matches) {
            
            // Verificar si el usuario trabajó en este partido usando el repositorio de UserMatch
            Optional<UserMatch> userMatchOpt = userMatchRepository.findByUserAndMatch(user, match);
            
            if (!userMatchOpt.isPresent()) {
                // Si el usuario trabajó en el partido, añadimos los detalles del trabajo
            	MatchWithUserInfoDTO matchWithUserInfo = new MatchWithUserInfoDTO();
                matchWithUserInfo.setMatch(convertToDTO(match));  // Convertimos el partido a DTO
                // Si no trabajó, sólo indicamos que no trabajó en este partido
                matchWithUserInfo.setUserWorked(false);
             // Añadimos el objeto a la lista de resultados
                result.add(matchWithUserInfo);
            }
        }
        return result; 
	}


	
	
	
}
