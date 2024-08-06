package com.irojas.demojwt.ServiceIntentary;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.irojas.demojwt.ModelInventary.Product;
import com.irojas.demojwt.ModelInventary.Tshirt;
import com.irojas.demojwt.RepositoryInventary.ProductRepository;
import com.irojas.demojwt.RepositoryInventary.TshirtRepository;


@Service
public class ProductService {
	
	private ProductRepository productRepository;
	private TshirtRepository tshirtRepository;
	
	
	public ProductService (ProductRepository productRepository, TshirtRepository tshirtRepository) {
		this.productRepository = productRepository;
		this.tshirtRepository = tshirtRepository;
	}
	
	
	// Métodos a realizar
		public List<Product> getAllProducts(){	
			return productRepository.findAll();
		}
		
		
		public Optional<Product> getProdcutById(Long id){
			return productRepository.findById(id);
		}
		
		
		
		
		public List<Product> getProductByPriceDescending(){
			
			List<Product> productList = this.getAllProducts();
			
			if(productList == null || productList.isEmpty()) {
				return Collections.emptyList();
			}else {
				// Ordenamos por precio
				productList.sort(Comparator.comparing(Product::getPrice).reversed());
			}
			return productList;
		}
		
		public List<Product> getProductByPriceAscending(){
			List<Product> productList = this.getAllProducts();
			
			if(productList == null || productList.isEmpty()) {
				return Collections.emptyList();
			}else {
				// Ordenamos por pre
				productList.sort(Comparator.comparing(Product::getPrice));
			}
			return productList;
		}
		
		public List<Product> getProductByStockAscending(){
			List<Product> productList = this.getAllProducts();
			
			if(productList == null || productList.isEmpty()) {
				return Collections.emptyList();
			}else {
				// Ordenamos por stock
				productList.sort(Comparator.comparing(Product::getTotalStock));
			}
			return productList;
		}
		
		public List<Product> getProductByStockDescending(){
			
			List<Product> productList = this.getAllProducts();
			
			if(productList == null || productList.isEmpty()) {
				return Collections.emptyList();
			}else {
				// Ordenamos por stock
				productList.sort(Comparator.comparing(Product::getTotalStock).reversed());
			}
			return productList;
			
		}
		
		public Product addProduct(Product product) {
	        // Save the product
	        Product savedProduct = productRepository.save(product);

	        // If the product is a t-shirt, add the variants
	        if (product.getIsTshirt() != null && product.getIsTshirt()) {
	            for (Tshirt tshirt : product.getTshirts()) {
	            	tshirt.setProduct(savedProduct);
	                tshirtRepository.save(tshirt);
	            }
	        }
	        return savedProduct;
	    }

		
	    public void deleteProduct(Long id) {
	        productRepository.deleteById(id);
	    }
	    

	    public Product updateProduct(Long id, Product productDetails) {
	        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	        product.setName(productDetails.getName());
	        product.setDescription(productDetails.getDescription());
	        product.setPrice(productDetails.getPrice());
	        product.setIsTshirt(productDetails.getIsTshirt());
	        product.setTotalStock(productDetails.getTotalStock());
	        
	        if (productDetails.getIsTshirt() != null && productDetails.getIsTshirt()) {
	            product.setTshirts(productDetails.getTshirts());
	            for (Tshirt tshirt : productDetails.getTshirts()) {
	            	tshirt.setProduct(product);
	                tshirtRepository.save(tshirt);
	            }
	        } else {
	            product.setTshirts(null);;
	        }
	        
	        return productRepository.save(product);
	    }
	
	
}
