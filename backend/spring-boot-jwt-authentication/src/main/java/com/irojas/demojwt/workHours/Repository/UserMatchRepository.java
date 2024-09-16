package com.irojas.demojwt.workHours.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.workHours.Model.Match;
import com.irojas.demojwt.workHours.Model.UserMatch;

public interface UserMatchRepository extends JpaRepository<UserMatch, Long> {
	Optional<UserMatch> findByUserAndMatch(User user, Match match);

}
