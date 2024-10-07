package com.irojas.demojwt.sportRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.sport.Model.League;

public interface LeagueRepository extends JpaRepository<League, Long>{

}
