package com.irojas.demojwt.workHours.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.workHours.Model.Money;
import com.irojas.demojwt.workHours.Model.Season;

public interface MoneyRepository extends JpaRepository<Money, Long>{

	Optional<Money> findBySeasonAndUser(Season season, User user);
	
}
