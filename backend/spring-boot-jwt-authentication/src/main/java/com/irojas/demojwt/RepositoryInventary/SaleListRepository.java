package com.irojas.demojwt.RepositoryInventary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.ModelInventary.Product;
import com.irojas.demojwt.ModelInventary.SaleList;


public interface SaleListRepository extends JpaRepository<SaleList, Long>{
	Optional<SaleList> findBySaleId(String SaleId);
}
