package com.irojas.demojwt.RepositoryInventary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irojas.demojwt.ModelInventary.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Optional<Product> findByPublicId(String publicId);
	
	void deleteByPublicId(String publicId);
	
}
