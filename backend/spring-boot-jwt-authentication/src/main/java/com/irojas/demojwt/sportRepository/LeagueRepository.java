package com.irojas.demojwt.sportRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.sport.Model.League;
import com.irojas.demojwt.sport.ModelDTO.PlayerLeagueDTO;

public interface LeagueRepository extends JpaRepository<League, Long>{
	
	public Optional<PlayerLeagueDTO> findAllByLeagueId(long id);
	
	
	
	
	//Cambiar
	public Optional<League> findByLeagueIdAndUser(long id);
	
}
