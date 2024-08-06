package com.irojas.demojwt.ServiceIntentary;

import java.util.List;
import java.util.Optional;

import com.irojas.demojwt.ModelInventary.Product;
import com.irojas.demojwt.RepositoryInventary.ProductRepository;
import com.irojas.demojwt.RepositoryInventary.TshirtRepository;

public class ProductService {
	
	private ProductRepository productRepository;
	private TshirtRepository tshirtRepository;
	
	
	public ProductService (ProductRepository productRepository, TshirtRepository tshirtRepository) {
		this.productRepository = productRepository;
		this.tshirtRepository = tshirtRepository;
	}
	
	
	// Métodos a realizar
		public List<Product> getAllProducts(){	
			return null;
		}
		
		
		public Optional<Product> getProdcutById(Long id){
			return null;
			
		}
		
		
		public Product addProduct(Product product) {
	        return productRepository.save(product);
	    }

		
	    public void deleteProduct(Long id) {
	        productRepository.deleteById(id);
	    }
	    

	    public Product updateProduct(Long id, Product productDetails) {
	        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	        product.setName(productDetails.getName());
	        product.setDescription(productDetails.getDescription());
	        product.setPrice(productDetails.getPrice());
	        product.setHasSizes(productDetails.getHasSizes());
	        if (productDetails.getHasSizes() != null && productDetails.getHasSizes()) {
	            product.setTshirts(productDetails.getTshirts());
	        } else {
	            product.setTshirts(null);
	        }
	        return productRepository.save(product);
	    }
	
	
}
