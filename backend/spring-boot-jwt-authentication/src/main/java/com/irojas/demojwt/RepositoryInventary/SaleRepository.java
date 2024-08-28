package com.irojas.demojwt.RepositoryInventary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.ModelInventary.ProductSale;


public interface SaleRepository extends JpaRepository<ProductSale, Long>{

}
