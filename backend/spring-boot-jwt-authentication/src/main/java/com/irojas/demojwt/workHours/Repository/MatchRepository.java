package com.irojas.demojwt.workHours.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.Model.User;
import com.irojas.demojwt.workHours.Model.Match;

public interface MatchRepository extends JpaRepository<Match, Long>{

	public List<Match> findAll();

	public Optional<Match> findById(Integer matchId) ;

}