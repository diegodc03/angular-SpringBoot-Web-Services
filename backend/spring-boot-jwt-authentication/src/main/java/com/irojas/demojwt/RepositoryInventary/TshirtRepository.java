package com.irojas.demojwt.RepositoryInventary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.ModelInventary.Garment;

public interface TshirtRepository extends JpaRepository<Garment, Long>{

}
