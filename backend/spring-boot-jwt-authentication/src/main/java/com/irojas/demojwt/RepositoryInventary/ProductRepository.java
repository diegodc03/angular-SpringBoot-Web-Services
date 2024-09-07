package com.irojas.demojwt.RepositoryInventary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.irojas.demojwt.ModelInventary.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Optional<Product> findByPublicId(String publicId);
	
	void deleteByPublicId(String publicId);
	
	
	@Query("SELECT p FROM Product p WHERE p.user.email = :email")
	Optional<List<Product>> findByUserEmail(@Param("email") String email);
	
	@Query("SELECT p FROM Product p WHERE p.user.email = :email ORDER BY p.price ASC")
	Optional<List<Product>> findByUserEmailOrderByPriceAsc(String email);
    
	@Query("SELECT p FROM Product p WHERE p.user.email = :email ORDER BY p.price DESC")
	Optional<List<Product>> findByUserEmailOrderByPriceDesc(String email);
    
	@Query("SELECT p FROM Product p WHERE p.user.email = :email ORDER BY p.totalStock ASC")
	Optional<List<Product>> findByUserEmailOrderByTotalStockAsc(String email);
    
	@Query("SELECT p FROM Product p WHERE p.user.email = :email ORDER BY p.totalStock DESC")
	Optional<List<Product>> findByUserEmailOrderByTotalStockDesc(@Param("email") String email);

	
	
}
