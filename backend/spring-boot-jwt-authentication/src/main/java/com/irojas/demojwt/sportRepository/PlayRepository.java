package com.irojas.demojwt.sportRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irojas.demojwt.sport.Model.Play;
import com.irojas.demojwt.sport.ModelDTO.PlayMatchDTOWithPlayers;

@Repository
public interface PlayRepository extends JpaRepository<Play, Long>{
	
	public Optional<List<Play>> getAllPlayByLeagueId(Long id);

	public Optional<Play> findMatchById(Long matchId);
}
