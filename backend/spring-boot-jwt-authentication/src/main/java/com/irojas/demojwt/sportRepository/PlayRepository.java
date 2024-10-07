package com.irojas.demojwt.sportRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irojas.demojwt.sport.Model.Play;

@Repository
public interface PlayRepository extends JpaRepository<Play, Long>{
	
	public Optional<List<Play>> getAllPlayByLeagueId(Long id);
}
