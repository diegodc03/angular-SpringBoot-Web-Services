package com.irojas.demojwt.ServiceIntentary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.irojas.demojwt.ModelInventary.Product;
import com.irojas.demojwt.ModelInventary.Tshirt;
import com.irojas.demojwt.ModelInventaryDTO.ProductDTO;
import com.irojas.demojwt.ModelInventaryDTO.TshirtDTO;
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
		
		
		public Product addProduct(ProductDTO productDTO) {
	        
			int[] stock = {0};  
			
			Product product = new Product();
	        product.setName(productDTO.getName());
	        product.setDescription(productDTO.getDescription());
	        product.setPrice(productDTO.getPrice());
	        product.setIsTshirt(productDTO.getIsTshirt());
	        product.setTotalStock(productDTO.getTotalStock());

	        // in lambda can use a variable created out of lambda if is not final. we can use a array
	        if(productDTO.getIsTshirt() && productDTO.getTshirts() != null) {
	        	List<Tshirt> tshirts = productDTO.getTshirts().stream().map(tshirtDTO -> {
		            stock[0] += tshirtDTO.getStock();   
		            
	        		Tshirt tshirt = new Tshirt();
		            tshirt.setSize(tshirtDTO.getSize());
		            tshirt.setColor(tshirtDTO.getColor());
		            tshirt.setMaterial(tshirtDTO.getMaterial());
		            tshirt.setStock(tshirtDTO.getStock());
		            tshirt.setProduct(product);
		            return tshirt;
		            
		        }).collect(Collectors.toList());
	        	
	        	product.setTotalStock(stock[0]);
		        product.setTshirts(tshirts);
	        }else {
	        	product.setTshirts(new ArrayList<>());
	        }
	        
	        // Automaticamnete las camisetas se guardan asociadas al producto cuando se guardan
	        return productRepository.save(product);
	    }
		
		
		
		
	    public void deleteProduct(Long id) {
	        productRepository.deleteById(id);
	    }
	    

	    public Product updateProduct(Long id, ProductDTO productDetailsDTO) {
	    	int[] stock = {0};  
	    	
	    	Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	        
	        product.setName(productDetailsDTO.getName());
	        product.setDescription(productDetailsDTO.getDescription());
	        product.setPrice(productDetailsDTO.getPrice());
	        product.setIsTshirt(productDetailsDTO.getIsTshirt());
	        product.setTotalStock(productDetailsDTO.getTotalStock());
	        
	        // Add the thirts added
	        List<Tshirt> p = product.getTshirts();
	       

	        if (productDetailsDTO.getIsTshirt()) {
	            List<Tshirt> updatedTshirts = new ArrayList<>();

	            // Procesar cada tshirtDTO recibido
	            for (TshirtDTO tshirtDTO : productDetailsDTO.getTshirts()) {
	            	
	                Tshirt tshirt = new Tshirt();
	                stock[0] += tshirtDTO.getStock();
	                tshirt.setSize(tshirtDTO.getSize());
	                tshirt.setColor(tshirtDTO.getColor());
	                tshirt.setMaterial(tshirtDTO.getMaterial());
	                tshirt.setStock(tshirtDTO.getStock());
	                tshirt.setProduct(product); // Asocia el nuevo tshirt con el producto
	                updatedTshirts.add(tshirt);
	                p.add(tshirt);
	                
	            }

	            // Asigna la nueva lista de tshirts al producto
	            //product.setTshirts(updatedTshirts);
	            product.setTotalStock(stock[0]);
	        } else {
	            // Si no es una camiseta, limpia la colección de tshirts
	            product.setTshirts(new ArrayList<>());
	        } 
	        return productRepository.save(product);
	    }  
	        
	        
	        
	
	
}
