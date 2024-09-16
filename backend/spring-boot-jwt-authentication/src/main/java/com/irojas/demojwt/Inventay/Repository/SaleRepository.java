package com.irojas.demojwt.Inventay.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.Inventay.Model.ProductSale;


public interface SaleRepository extends JpaRepository<ProductSale, Long>{

}
