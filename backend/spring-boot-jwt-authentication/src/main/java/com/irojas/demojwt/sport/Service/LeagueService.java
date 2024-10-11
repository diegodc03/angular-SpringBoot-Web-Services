package com.irojas.demojwt.sport.Service;



import java.util.Optional;

import com.irojas.demojwt.sport.Model.League;
import com.irojas.demojwt.sport.ModelDTO.LeagueDTO;
import com.irojas.demojwt.sport.ModelDTO.PlayerLeagueDTO;
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



	public LeagueDTO getAllLeagues(LeagueDTO leagueDTO, String string) {
		// TODO Auto-generated method stub
		try {
			Optional<League> optLeague = this.leagueRepository.findById(leagueDTO.getId());
			if(optLeague.isPresent()) {
				LeagueDTO returnleagueDTO = new LeagueDTO(optLeague.get().getId(), optLeague.get().getName(), optLeague.get().isRequireRequest());
				return returnleagueDTO;
			}
			return null;
			
		}catch(Exception e){
			throw new RuntimeException("Failed to get all leagues the league ", e);
		}
	}



	public LeagueDTO getLeaguesJoined(LeagueDTO leagueDTO, String username) {
		// TODO Auto-generated method stub
		try {
	
			
			Optional<League> optLeague = this.leagueRepository.findById(leagueDTO.getId());
			if(optLeague.isPresent()) {
				LeagueDTO returnleagueDTO = new LeagueDTO(optLeague.get().getId(), optLeague.get().getName());
				return returnleagueDTO;
			}
			return null;
			
		}catch(Exception e){
			throw new RuntimeException("Failed to getLeagues of user joined", e);
		}
	}



	public PlayerLeagueDTO getLeagueInfo(Long leagueId) {
		// TODO Auto-generated method stub
		try {
			Optional<PlayerLeagueDTO> playerLeagueDTO = this.leagueRepository.findAllByLeagueId(leagueId);
			if(playerLeagueDTO.isPresent()) {
				return playerLeagueDTO.get();
			}
			return null;
			
		}catch(Exception e){
			throw new RuntimeException("Failed to getLeagueInfo the league ", e);
		}
		
		
		
	}
	
	
	
}
