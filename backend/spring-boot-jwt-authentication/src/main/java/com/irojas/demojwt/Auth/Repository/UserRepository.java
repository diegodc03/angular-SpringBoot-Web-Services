package com.irojas.demojwt.Auth.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.irojas.demojwt.Auth.Model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email); 
    
    @Modifying()
    @Query("update User u set u.firstname=:firstname, u.lastname=:lastname, u.country=:country where u.id = :id")
    void updateUser(@Param(value = "id") Long id,   @Param(value = "firstname") String firstname, @Param(value = "lastname") String lastname , @Param(value = "country") String country);

    
    
}
