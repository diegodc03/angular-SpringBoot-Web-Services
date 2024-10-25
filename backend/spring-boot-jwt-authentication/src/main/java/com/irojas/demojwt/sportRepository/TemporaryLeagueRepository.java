package com.irojas.demojwt.sportRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irojas.demojwt.sport.Model.TemporaryLeague;

@Repository
public interface TemporaryLeagueRepository extends JpaRepository<TemporaryLeague, Long>{
	Optional<TemporaryLeague> findByEmail(String email);
}
