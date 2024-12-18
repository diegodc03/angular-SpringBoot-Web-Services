package com.irojas.demojwt.Inventay.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.ModelDTO.UserDTO;
import com.irojas.demojwt.Inventay.Model.SizeElement;
import com.irojas.demojwt.Inventay.Model.Product;
import com.irojas.demojwt.Inventay.Model.Size;
import com.irojas.demojwt.Inventay.ModelDTO.SizeElementDTO;
import com.irojas.demojwt.Inventay.ModelDTO.ProductDTO;
import com.irojas.demojwt.Inventay.Repository.SizeElementRepository;
import com.irojas.demojwt.Inventay.Repository.ProductRepository;
import com.irojas.demojwt.Jwt.JwtTokenManager;



@Service
public class ProductService {
	
	private ProductRepository productRepository;
	private JwtTokenManager jwtTokenManager;
	private SizeElementRepository tshirtRepository;
	private static String IMAGE_DIRECTORY = "src//main//resources//static/uploads/";
	
	public ProductService (ProductRepository productRepository, SizeElementRepository tshirtRepository, JwtTokenManager jwtTokenManager) {
		this.productRepository = productRepository;
		this.tshirtRepository = tshirtRepository;
		this.jwtTokenManager = jwtTokenManager;
	}
	
	
	// Métodos a realizar
	public List<Product> getAllProducts(){
		List<Product> p = productRepository.findAll();
		return p;
	}
	

	public List<Product> getProductsByEmail(String email) {
        Optional<List<Product>> optProductsList = productRepository.findByUserEmail(email);
        if(optProductsList.isPresent()) {
        	return optProductsList.get();
        }
        return null;
    }
		
	public Optional<Product> getProductByPublicId(String publicId){
		return productRepository.findByPublicId(publicId);
	}
	
	public Optional<Product> getProductById(Long id){
		return productRepository.findById(id);
	}
		
		
	public List<Product> getProductByPriceDescending(String email) {
        Optional<List<Product>> optProductsList = productRepository.findByUserEmailOrderByPriceDesc(email);
        if(optProductsList.isPresent()) {
        	return optProductsList.get();
        }
        return null;
    }

    public List<Product> getProductByPriceAscending(String email) {
    	Optional<List<Product>> optProductsList = productRepository.findByUserEmailOrderByPriceAsc(email);
    	if(optProductsList.isPresent()) {
        	return optProductsList.get();
        }
        return null;
    }

    public List<Product> getProductByStockAscending(String email) {
    	Optional<List<Product>> optProductsList = productRepository.findByUserEmailOrderByTotalStockAsc(email);
    	if(optProductsList.isPresent()) {
        	return optProductsList.get();
        }
        return null;
    }

    public List<Product> getProductByStockDescending(String email) {
    	Optional<List<Product>> optProductsList = productRepository.findByUserEmailOrderByTotalStockDesc(email);
    	if(optProductsList.isPresent()) {
        	return optProductsList.get();
        }
        return null;
    }
		
		
		public Product addProduct(ProductDTO productDTO, String token) {
			
			// Aqui recogemos un usuario, pero no lo estamos vuscando
	        User user = this.jwtTokenManager.getUserFromToken(token);
	        
			int[] stock = {0};  
			
			Product product = new Product();
	        product.setName(productDTO.getName());
	        product.setDescription(productDTO.getDescription());
	        product.setPrice(productDTO.getPrice());
	        product.setIsTshirt(productDTO.getIsTshirt());
	        product.setTotalStock(productDTO.getTotalStock());
	        product.setPublicId();
	        
	        
	        // in lambda can use a variable created out of lambda if is not final. we can use a array
	        if(productDTO.getIsTshirt() && productDTO.getGarments() != null) {
	
	        	List<SizeElement> sizeElements = productDTO.getGarments().stream().map(tshirtDTO -> {
		            stock[0] += tshirtDTO.getStock();   

	        		SizeElement sizeElement = new SizeElement();
	        		
	        		
	        		Size size = Size.valueOf(tshirtDTO.getSize().toUpperCase());
		            if(size != null) {
		            	sizeElement.setSize(size);
		            }
		            sizeElement.setColor(tshirtDTO.getColor());
		            sizeElement.setMaterial(tshirtDTO.getMaterial());
		            sizeElement.setStock(tshirtDTO.getStock());
		            sizeElement.setProduct(product);
		            return sizeElement;
		            
		        }).collect(Collectors.toList());
	        	
	        	product.setTotalStock(stock[0]);
		        product.setGarments(sizeElements);
	        }else {
	        	product.setGarments(new ArrayList<>());
	        }
	        // Automaticamnete las camisetas se guardan asociadas al producto cuando se guardan
	        product.setUser(user);
	        return productRepository.save(product);
	    }
		
		
		public Product uploadProductImage(String publicId, MultipartFile image) {
			// TODO Auto-generated method stub
			System.out.println("hola");
			Optional<Product> optionalProduct = productRepository.findByPublicId(publicId);
			
			if(optionalProduct.isPresent()) {
				
				Product product = optionalProduct.get();
				
				if (image != null && !image.isEmpty()) {
		            String imageName = publicId + "_image"; // o .png, según corresponda
		            Path imagePath = Paths.get(IMAGE_DIRECTORY + imageName);

		            // Crear el directorio si no existe
		            try {
						Files.createDirectories(imagePath.getParent());
						Files.write(imagePath, image.getBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		            // Asignar el nombre de la imagen al producto
		            product.setImageName(imageName);
		            
		        }
				return productRepository.save(product);
			}
			return null;
	    }
		
		
		
	    public void deleteProduct(String publicId) {
	    	System.out.println(publicId);
	    	
	    	Optional <Product> optDeletingProduct = this.getProductByPublicId(publicId);
	    	
	    	if(optDeletingProduct.isPresent()) {
	    		productRepository.delete(optDeletingProduct.get());
	    	}
	    }
	    

	    public Product updateProduct(String productId, ProductDTO productDetailsDTO) {
	    
	    	int totalStock = 0;
	    	
	    	Optional<Product> optProduct = Optional.of(productRepository.findByPublicId(productId).orElseThrow(() -> new RuntimeException("Product not found")));
	        
	    	if(optProduct.isPresent()) {
	    		Product product = optProduct.get();
	    	
	    	
		        product.setName(productDetailsDTO.getName());
		        product.setDescription(productDetailsDTO.getDescription());
		        product.setPrice(productDetailsDTO.getPrice());
		        product.setIsTshirt(productDetailsDTO.getIsTshirt());
		        
	
		        if (productDetailsDTO.getIsTshirt()) {
		        	
		        	// Add the thirts added
		        	// this garments have not been updated yet
			        List<SizeElement> existingTshirts = product.getGarments();
		        	
		            // Procesar cada tshirtDTO recibido
		            for (SizeElementDTO sizeElementDTO : productDetailsDTO.getGarments()) {
		            	
		            	SizeElement sizeElement = existingTshirts.stream()
		                        .filter(g -> g.getSize().equals(Size.valueOf(sizeElementDTO.getSize().toUpperCase())))
		                        .findFirst()
		                        .orElse(new SizeElement());
		            	
		            	
		            	Size size = Size.valueOf(sizeElementDTO.getSize().toUpperCase());
		                sizeElement.setSize(size);
		                sizeElement.setColor(sizeElementDTO.getColor());
		                sizeElement.setMaterial(sizeElementDTO.getMaterial());
		                sizeElement.setStock(sizeElementDTO.getStock());
		                sizeElement.setProduct(product);
		            
		                
		                totalStock += sizeElementDTO.getStock();
		                
		                // if the garment exists, update the variables, but if not exists add it in the array
		                if (!existingTshirts.contains(sizeElement)) {
		                    existingTshirts.add(sizeElement);
		                }
		            }
		            // Limpiar las camisetas que ya no están presentes en los detalles del producto
		            existingTshirts.removeIf(g -> productDetailsDTO.getGarments().stream()
		                    .noneMatch(dto -> dto.getSize().equalsIgnoreCase(g.getSize().toString())));
		            
		            // Asigna la nueva lista de tshirts al producto
		            //product.setTshirts(updatedTshirts);
		            product.setTotalStock(totalStock);
		            
		        } else {
		           
		         // Si no es camiseta, eliminar todas las camisetas asociadas
		            product.getGarments().clear();
		            product.setTotalStock(productDetailsDTO.getTotalStock());
		        } 
		        return productRepository.save(product);
	    	}
	    	return null;
	    }



		
	        
	        
	        
	
	
}
