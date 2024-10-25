package com.irojas.demojwt.sportRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irojas.demojwt.sport.Model.League;
import com.irojas.demojwt.sport.Model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

	Optional<Player> findByUserId(Long id);
	
	

	
	
	
	
}
