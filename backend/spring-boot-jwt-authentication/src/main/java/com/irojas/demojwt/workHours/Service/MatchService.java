package com.irojas.demojwt.workHours.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.web.multipart.MultipartFile;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.Repository.UserRepository;
import com.irojas.demojwt.workHours.ModelDTO.MatchWithUserInfoDTO;
import com.irojas.demojwt.workHours.Model.Match;
import com.irojas.demojwt.workHours.Model.Season;
import com.irojas.demojwt.workHours.Model.UserMatch;
import com.irojas.demojwt.workHours.Model.WorkingRoles;
import com.irojas.demojwt.workHours.ModelDTO.MatchDTO;
import com.irojas.demojwt.workHours.ModelDTO.SeasonDTO;
import com.irojas.demojwt.workHours.Repository.MatchRepository;
import com.irojas.demojwt.workHours.Repository.SeasonRepository;
import com.irojas.demojwt.workHours.Repository.UserMatchRepository;

public class MatchService {


    private MatchRepository matchRepository;

    private UserRepository userRepository;

    private UserMatchRepository userMatchRepository;

    private SeasonRepository seasonRepository;
    
    
    // Probar
    // Obtener todos los partidos de la temporada (usuarios pueden ver todos)
    public List<MatchDTO> getAllMatches() {
        List<Match> matches = matchRepository.findAll();
        return matches.stream()
                .map(MatchDTO::new)
                .collect(Collectors.toList());
    }
    
    // Probar
    public List<MatchDTO> getAllSeasonMatches(SeasonDTO season){
    	
    	Optional<Season> optSeason = seasonRepository.findBySeasonName(season.getSeasonName());
    	if(!optSeason.isPresent()) {
    		return null;
    	}
    	
    	return optSeason.get().getMatches().stream().map(MatchDTO::new).collect(Collectors.toList());
    
    }
    
     
    

    
    // Añadir un nuevo partido a la temporada (solo admin)
    public String addMatch(MatchDTO matchDTO, SeasonDTO seasonDTO) {
    	
    	// check if the season already exists
    	List<Season> existanceSeasonList = seasonRepository.findAll();
		if(existanceSeasonList.size() == 0 || existanceSeasonList.stream().anyMatch(s -> s.getSeasonName().equals(seasonDTO.getSeasonName()))) {
			return null;
		}
		
		Optional<Season> optSeason= seasonRepository.findBySeasonName(seasonDTO.getSeasonName());
		
		if(!optSeason.isPresent()) {
			return null;
		}
		Season season = optSeason.get();
		
		
        Match match = new Match();
        match.setMatchDate(matchDTO.getDate());
        match.setDescription(matchDTO.getDescription());
        match.setSeason(season);
        
        List<Match> seasonMatches = season.getMatches();
        
        seasonMatches.add(match);
        
      
        // Resto de asignaciones desde el DTO al objeto Match...
        seasonRepository.save(season);
        //matchRepository.save(match);
        return null;
    }

   

    
    
    
    
	public List<MatchWithUserInfoDTO> getAllMatchesOfSeasonWithUserInfo(Integer seasonId, String username) {
		// TODO Auto-generated method stub
		List<Match> matches = matchRepository.findBySeasonId(seasonId);
		
		// Buscar el usuario autenticado por su username
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
     // Lista para almacenar los resultados
        List<MatchWithUserInfoDTO> result = new ArrayList<>();
        
     // Recorrer los partidos de la temporada
        for (Match match : matches) {
            MatchWithUserInfoDTO matchWithUserInfo = new MatchWithUserInfoDTO();
            matchWithUserInfo.setMatch(convertToDTO(match));  // Convertimos el partido a DTO

            // Verificar si el usuario trabajó en este partido usando el repositorio de UserMatch
            Optional<UserMatch> userMatchOpt = userMatchRepository.findByUserAndMatch(user, match);
            
            if (userMatchOpt.isPresent()) {
                // Si el usuario trabajó en el partido, añadimos los detalles del trabajo
                UserMatch userMatch = userMatchOpt.get();
                matchWithUserInfo.setUserWorked(true);
                matchWithUserInfo.setRole(userMatch.getWorkingRol());
                matchWithUserInfo.setPayment(userMatch.getWorkingRol().getSalary());
            } else {
                // Si no trabajó, sólo indicamos que no trabajó en este partido
                matchWithUserInfo.setUserWorked(false);
                matchWithUserInfo.setRole(null);  // Sin rol
                matchWithUserInfo.setPayment(null);  // Sin pago
            }

            // Añadimos el objeto a la lista de resultados
            result.add(matchWithUserInfo);
        }

        return result;
	}
	
	
	// Método para convertir Match a MatchDTO
    private MatchDTO convertToDTO(Match match) {
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setId(match.getId());
        matchDTO.setDate(match.getMatchDate());
        // Añadir otros campos relevantes de "Match"
        return matchDTO;
    }

	
	
	
	
}
