package com.irojas.demojwt.sportRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.sport.Model.League;
import com.irojas.demojwt.sport.ModelDTO.League2DTO;

public interface LeagueRepository extends JpaRepository<League, Long>{
	

	
	
	
	
	//Cambiar
	public Optional<League> findByLeagueIdAndUser(long id);
	
}
