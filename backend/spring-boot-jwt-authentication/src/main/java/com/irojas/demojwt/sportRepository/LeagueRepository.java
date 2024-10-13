package com.irojas.demojwt.sportRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.sport.Model.League;
import com.irojas.demojwt.sport.ModelDTO.LeagueInformationDTO;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long>{
	

	Optional<League> findById(Long id);
	
	
	

	
	
}
