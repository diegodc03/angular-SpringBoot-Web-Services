package com.irojas.demojwt.Auth.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.Auth.Model.TemporaryUser;

public interface TemporaryUserRepository extends JpaRepository<TemporaryUser, Long>{

	Optional<TemporaryUser> findTemporaryUserByToken(String token);

	Optional<TemporaryUser> findByEmail(String email);
	
}
