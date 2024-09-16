package com.irojas.demojwt.Inventay.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.irojas.demojwt.Inventay.Model.Product;
import com.irojas.demojwt.Inventay.Model.SaleList;


public interface SaleListRepository extends JpaRepository<SaleList, Long>{
	Optional<SaleList> findBySaleId(String SaleId);

	@Query("SELECT p FROM SaleList p WHERE p.user.email = :email")
	Optional<List<SaleList>> findSaleListByUserEmail(String email);
	
}
