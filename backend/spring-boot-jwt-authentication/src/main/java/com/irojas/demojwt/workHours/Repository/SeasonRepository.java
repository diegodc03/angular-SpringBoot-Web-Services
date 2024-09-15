package com.irojas.demojwt.workHours.Repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.workHours.Model.Season;


public interface SeasonRepository extends JpaRepository<Season, Long> {

	Optional<Season> findBySeasonName(LocalDate seasonName);
	
	
}
