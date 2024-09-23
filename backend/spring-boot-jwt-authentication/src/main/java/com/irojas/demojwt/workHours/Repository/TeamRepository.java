package com.irojas.demojwt.workHours.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.workHours.Model.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{

	Team findByTeamName(String teamName);
	
}
