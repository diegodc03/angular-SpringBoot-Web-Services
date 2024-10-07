package com.irojas.demojwt.sport.Service;



import java.util.Optional;

import com.irojas.demojwt.sport.Model.League;
import com.irojas.demojwt.sport.ModelDTO.LeagueDTO;
import com.irojas.demojwt.sportRepository.LeagueRepository;

public class LeagueService {
	
	private LeagueRepository leagueRepository;
	
	private LeagueService(LeagueRepository leagueRepository) {
		this.leagueRepository = leagueRepository;
	}
	
	

    public void createLeague(LeagueDTO leagueDTO) {
        try {
            League league = new League();
            league.setName(leagueDTO.getName());
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
	
	
	
}
