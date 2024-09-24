package com.irojas.demojwt.workHours.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.irojas.demojwt.workHours.Model.Match;
import com.irojas.demojwt.workHours.Model.Season;
import com.irojas.demojwt.workHours.Model.Team;
import com.irojas.demojwt.workHours.ModelDTO.SeasonDTO;
import com.irojas.demojwt.workHours.Repository.MatchRepository;
import com.irojas.demojwt.workHours.Repository.SeasonRepository;
import com.irojas.demojwt.workHours.Repository.TeamRepository;

@Service
public class SeasonService {

	private SeasonRepository seasonRepository;
	
	private TeamRepository teamRepository;
	
	
	public SeasonService(SeasonRepository seasonRepository, TeamRepository teamRepository) {
		this.seasonRepository = seasonRepository;
		this.teamRepository = teamRepository;
		
	}
	
	
	public List<SeasonDTO> findAllSeasons() {
		return this.seasonRepository.findAll()
				.stream()
				.map(SeasonDTO::new)
				.collect(Collectors.toList());
	}
	
	
	
	
// OK
	public String addSeasonWithMatchesFromFile(MultipartFile file, SeasonDTO seasonDTO) throws Exception {
	    // Crear una nueva temporada con el nombre que proporciona el usuario
	    
		
		List<Season> existanceSeasonList = seasonRepository.findAll();
		if(existanceSeasonList.stream().anyMatch(s -> s.getSeasonName().equals(seasonDTO.getSeasonName()))) {
			throw new Exception("Season already exists: " + seasonDTO.getSeasonName());
		}
		
		
		Season season = new Season();
	    season.setSeasonName(seasonDTO.getSeasonName()); 

	    

	    // Leer el archivo
	    BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
	    String line;
	    List<Match> matches = new ArrayList<>();
	    
	    // Procesar cada línea del archivo
	    while ((line = reader.readLine()) != null) {
	        // Identificamos si la línea corresponde a una jornada
	        if (line.startsWith("Jornada")) {
	            // Extraer número de jornada, fecha y equipos
	            String jornadaInfo = line.substring(0, line.indexOf(":")).trim();  // Ejemplo: "Jornada 1 (27 de septiembre)"
	            //String matchInfo = line.substring(line.indexOf(":") + 1).trim();  // Ejemplo: "Hestia Menorca vs. Caja Rural CB Zamora"
	            //String localTeam = line.substring((line.indexOf(":") + 1).trim(), (line.indexOf("vs.") - 1).trim();
	            
	            int vsIndex = line.indexOf("vs.");
	         // Extrae los nombres de los equipos
	            String localTeam = line.substring(line.indexOf(":") + 1, vsIndex).trim();
	            String awayTeam = line.substring(vsIndex + 3).trim();
	            
	            Team localTeamO = new Team(localTeam);
	            Team awayTeamO = new Team(awayTeam);

	            // Verificar si el equipo local ya existe
	            Team existingLocalTeam = teamRepository.findByTeamName(localTeamO.getTeamName());
	            if (existingLocalTeam == null) {
	                teamRepository.save(localTeamO);
	            } else {
	                localTeamO = existingLocalTeam; // Usar el equipo existente
	            }

	            // Verificar si el equipo visitante ya existe
	            Team existingAwayTeam = teamRepository.findByTeamName(awayTeamO.getTeamName());
	            if (existingAwayTeam == null) {
	                teamRepository.save(awayTeamO);
	            } else {
	                awayTeamO = existingAwayTeam; // Usar el equipo existente
	            }
	            
	            
	            // Extraer fecha
	            String dateString = jornadaInfo.substring(jornadaInfo.indexOf("(") + 1, jornadaInfo.indexOf(")"));
	            LocalDate matchDate = parseDate(dateString);
	            
	            // Crear un nuevo partido
	            Match match = new Match();
	            match.setLocalTeam(localTeamO.getTeamName());
	            match.setAwayTeam(awayTeamO.getTeamName());
	            match.setDescription();
	            match.setMatchDate(matchDate);
	            match.setSeason(season);  // Asociar el partido con la nueva temporada
	            
	            if(match.getDescription().contains("CB ZAMORA VS.") || !match.getDescription().contains("VS. CB ZAMORA")) {
	            	match.setIs_local(true);
	            }else {
	            	match.setIs_local(false);
	            }
	            
	            // Añadir el partido a la lista
	            matches.add(match);
	        }
	    }
	    season.setMatches(matches);
	    // Guardar todos los partidos en la base de datos
	    //matchRepository.saveAll(matches);
	    seasonRepository.save(season);  // Guardar la nueva temporada en la base de datos
	    
	    return null;
	    
	}

	// Método auxiliar para parsear la fecha
	private LocalDate parseDate(String dateString) {
		
		
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
	    return LocalDate.parse(dateString, formatter);
	}


	public void deleteSeasonById(Long id) {
		// TODO Auto-generated method stub
		Optional<Season> optSeason = this.seasonRepository.findById(id);
		if(optSeason.isPresent()) {
			this.seasonRepository.delete(optSeason.get());
		}
		
	}

}
