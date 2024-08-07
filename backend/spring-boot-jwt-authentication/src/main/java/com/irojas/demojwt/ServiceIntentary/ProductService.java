package com.irojas.demojwt.ServiceIntentary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.irojas.demojwt.ModelInventary.Product;
import com.irojas.demojwt.ModelInventary.Size;
import com.irojas.demojwt.ModelInventary.Garment;
import com.irojas.demojwt.ModelInventaryDTO.ProductDTO;
import com.irojas.demojwt.ModelInventaryDTO.GarmentDTO;
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
		List<Product> p = productRepository.findAll();
		return p;
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
	        if(productDTO.getIsTshirt() && productDTO.getGarments() != null) {
	        	List<Garment> garments = productDTO.getGarments().stream().map(tshirtDTO -> {
		            stock[0] += tshirtDTO.getStock();   

	        		Garment garment = new Garment();
	        		
	        		
	        		Size size = Size.valueOf(tshirtDTO.getSize().toUpperCase());
		            if(size != null) {
		            	garment.setSize(size);
		            }
		            garment.setColor(tshirtDTO.getColor());
		            garment.setMaterial(tshirtDTO.getMaterial());
		            garment.setStock(tshirtDTO.getStock());
		            garment.setProduct(product);
		            return garment;
		            
		        }).collect(Collectors.toList());
	        	
	        	product.setTotalStock(stock[0]);
		        product.setGarments(garments);
	        }else {
	        	product.setGarments(new ArrayList<>());
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
	        List<Garment> existingTshirts = product.getGarments();
	       

	        if (productDetailsDTO.getIsTshirt()) {

	            // Procesar cada tshirtDTO recibido
	            for (GarmentDTO garmentDTO : productDetailsDTO.getGarments()) {
	            	
	            	// Comprobar si ya existe una camiseta con la misma talla
	                existingTshirts.removeIf(existingTshirt -> existingTshirt.getSize().equals(garmentDTO.getSize()));
	            	
	                Garment garment = new Garment();
	                
	                stock[0] += garmentDTO.getStock();
	                Size size = Size.valueOf(garmentDTO.getSize().toUpperCase());
		            if(size != null) {
		            	garment.setSize(size);
		            }
	                garment.setColor(garmentDTO.getColor());
	                garment.setMaterial(garmentDTO.getMaterial());
	                garment.setStock(garmentDTO.getStock());
	                garment.setProduct(product); // Asocia el nuevo tshirt con el producto

	                existingTshirts.add(garment);
	            }
	            // Asigna la nueva lista de tshirts al producto
	            //product.setTshirts(updatedTshirts);
	            product.setTotalStock(stock[0]);
	        } else {
	            // Si no es una camiseta, limpia la colección de tshirts
	            product.setGarments(new ArrayList<>());
	        } 
	        return productRepository.save(product);
	    }  
	        
	        
	        
	
	
}
