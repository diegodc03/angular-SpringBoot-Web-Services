package com.irojas.demojwt.workHours.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.irojas.demojwt.workHours.Model.Match;
import com.irojas.demojwt.workHours.Model.Season;
import com.irojas.demojwt.workHours.ModelDTO.SeasonDTO;
import com.irojas.demojwt.workHours.Repository.MatchRepository;
import com.irojas.demojwt.workHours.Repository.SeasonRepository;

public class SeasonService {

	private SeasonRepository seasonRepository;
	
	private MatchRepository matchRepository;
	
	public String addSeasonWithMatchesFromFile(MultipartFile file, SeasonDTO seasonDTO) throws IOException {
	    // Crear una nueva temporada con el nombre que proporciona el usuario
	    
		
		List<Season> existanceSeasonList = seasonRepository.findAll();
		if(existanceSeasonList.size() == 0 || existanceSeasonList.stream().anyMatch(s -> s.getSeasonName().equals(seasonDTO.getSeasonName()))) {
			return null;
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
	            String matchInfo = line.substring(line.indexOf(":") + 1).trim();  // Ejemplo: "Hestia Menorca vs. Caja Rural CB Zamora"
	            
	            // Extraer fecha
	            String dateString = jornadaInfo.substring(jornadaInfo.indexOf("(") + 1, jornadaInfo.indexOf(")"));
	            LocalDate matchDate = parseDate(dateString);
	            
	            // Crear un nuevo partido
	            Match match = new Match();
	            match.setDescription(matchInfo);
	            match.setMatchDate(matchDate);
	            match.setSeason(season);  // Asociar el partido con la nueva temporada
	            
	            // Añadir el partido a la lista
	            matches.add(match);
	        }
	    }

	    // Guardar todos los partidos en la base de datos
	    //matchRepository.saveAll(matches);
	    seasonRepository.save(season);  // Guardar la nueva temporada en la base de datos
	    
	    return null;
	    
	}

	// Método auxiliar para parsear la fecha
	private LocalDate parseDate(String dateString) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM", new Locale("es", "ES"));
	    return LocalDate.parse(dateString, formatter);
	}

}
