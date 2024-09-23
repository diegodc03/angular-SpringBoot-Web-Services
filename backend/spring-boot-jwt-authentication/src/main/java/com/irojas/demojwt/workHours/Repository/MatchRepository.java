package com.irojas.demojwt.workHours.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.workHours.Model.Match;

public interface MatchRepository extends JpaRepository<Match, Long>{

	public List<Match> findAll();

	public Optional<Match> findById(Integer matchId) ;

	public List<Match> findBySeasonId(Integer seasonId);
	
	public Optional<Match> findByMatchDate(LocalDate date);
	
	void deleteById(Long id);
	

	@Query("SELECT m FROM Match m WHERE m.season.id = :seasonId ORDER BY m.matchDate ASC")
	public List<Match> findBySeasonIdOrderedByDate(@Param("seasonId") Integer seasonId);

}
