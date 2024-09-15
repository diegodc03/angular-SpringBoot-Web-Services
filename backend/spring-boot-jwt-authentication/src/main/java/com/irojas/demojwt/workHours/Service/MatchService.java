package com.irojas.demojwt.workHours.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.web.multipart.MultipartFile;

import com.irojas.demojwt.Repsository.UserRepository;
import com.irojas.demojwt.workHours.Model.Match;
import com.irojas.demojwt.workHours.Model.Season;
import com.irojas.demojwt.workHours.Model.UserMatch;
import com.irojas.demojwt.workHours.Model.WorkingRoles;
import com.irojas.demojwt.workHours.ModelDTO.MatchDTO;
import com.irojas.demojwt.workHours.ModelDTO.SeasonDTO;
import com.irojas.demojwt.workHours.Repository.MatchRepository;
import com.irojas.demojwt.workHours.Repository.SeasonRepository;
import com.irojas.demojwt.workHours.Repository.UserMatchRepository;
import com.irojas.demojwt.Model.User;

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
    
    
    
    
    
    
    
    
    

    // Solo el usuario puede añadir o actualizar su trabajo y pago en partidos pasados o en la misma fecha
    public void addOrUpdateWorkAndPayment(Integer matchId, String username, WorkingRoles role) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found"));

        // Verificar que el partido sea pasado o en la misma fecha actual
        if (match.getMatchDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("Cannot add work or payment for future matches.");
        }

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserMatch userMatch = userMatchRepository.findByUserAndMatch(user, match)
                .orElse(new UserMatch(user, match));  // Si no existe la relación, la creamos.

        // Añadir los datos de trabajo y el pago
        userMatch.setWorkingRol(role);

        userMatchRepository.save(userMatch);
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

    // Cargar partidos desde un archivo .txt (para admin)
    public void addSeasonWithMatchesFromFile(MultipartFile file) throws IOException {
        // Lógica para procesar el archivo .txt y añadir la temporada con los partidos
    }

	
	
	
	
}
