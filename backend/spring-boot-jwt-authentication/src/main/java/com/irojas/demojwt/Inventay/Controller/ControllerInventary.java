package com.irojas.demojwt.Inventay.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.irojas.demojwt.Inventay.Model.Garment;
import com.irojas.demojwt.Inventay.Model.Product;
import com.irojas.demojwt.Inventay.ModelDTO.ProductDTO;
import com.irojas.demojwt.Inventay.Service.ProductService;
import com.irojas.demojwt.Jwt.JwtTokenManager;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventary")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class ControllerInventary {

	private ProductService productService;
	private JwtTokenManager jwtTokenManager;
	private static String IMAGE_DIRECTORY = "src/main/resources/static/uploads/";

	public ControllerInventary(ProductService productService, JwtTokenManager jwtTokenManager) {
		super();
		this.productService = productService;
		this.jwtTokenManager = jwtTokenManager;
	}


		@GetMapping("all-inventary")
	    public ResponseEntity<List<Product>> getAllProducts(
	    		@AuthenticationPrincipal UserDetails currentUser) {
			
	        List<Product> products = productService.getProductsByEmail(currentUser.getUsername());
	        
	        if(products != null) {
	        	return ResponseEntity.ok(products);
	        }
			return ResponseEntity.notFound().build();
	    }
	    
	    
	    @GetMapping("/inventary/{publicId}")
	    public ResponseEntity<Product> getProductById(@PathVariable String publicId) {
	        Optional<Product> product = productService.getProductByPublicId(publicId);
	        if(product.isPresent()) {
	        	return ResponseEntity.ok(product.get());
	        }
	        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }
	    
	    
	    @GetMapping("/price-ascending")
	    public ResponseEntity<List<Product>> getProductsByPriceAscending(
	    		@AuthenticationPrincipal UserDetails currentUser) {
	    	
			List<Product> products = productService.getProductByPriceAscending(currentUser.getUsername());
			if(products != null) {
	        	return ResponseEntity.ok(products);
	        }
			return ResponseEntity.notFound().build();
	    }
	    
	    
	    @GetMapping("/price-descending")
	    public ResponseEntity<List<Product>> getProductsByPriceDescending(
	    		@AuthenticationPrincipal UserDetails currentUser) {
	    	
	    	List<Product> products = productService.getProductByPriceDescending(currentUser.getUsername());
	        if(products != null) {
	        	return ResponseEntity.ok(products);
	        }
			return ResponseEntity.notFound().build();
	    }
	    
	    @GetMapping("/stock-ascending")
	    public ResponseEntity<List<Product>> getProductsByStockAscending(
	    		@AuthenticationPrincipal UserDetails currentUser) {
	
	    	List<Product> products = productService.getProductByStockAscending(currentUser.getUsername());
	        if(products != null) {
	        	return ResponseEntity.ok(products);
	        }
			return ResponseEntity.notFound().build();
	    }
	    
	    @GetMapping("/stock-descending")
	    public ResponseEntity<List<Product>> getProductsByStockDescending(
	    		@AuthenticationPrincipal UserDetails currentUser) {
	    	
	    	List<Product> products = productService.getProductByStockDescending(currentUser.getUsername());
	        if(products != null) {
	        	return ResponseEntity.ok(products);
	        }
			return ResponseEntity.notFound().build();
	    }
	    
	    public String returnEmail(HttpServletRequest request) {
	    	String token = request.getHeader(HttpHeaders.AUTHORIZATION).substring(7);
			return this.jwtTokenManager.getEmailFromToken(token); // Decodificar el token para obtener el em
	    }
	    


	    //Correcto
	    @PostMapping("/add-product-info")
	    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO, HttpServletRequest request) {
	    	String token = request.getHeader(HttpHeaders.AUTHORIZATION).substring(7);
	        Product product = productService.addProduct(productDTO, token);
	        
	   
	        return ResponseEntity.ok(product.getPublicId());
	    }
	    


		@PostMapping("/image/{productId}")
		public ResponseEntity<String> uploadProductImage(
		        @PathVariable String productId,
		        @RequestParam("file") MultipartFile image) {
			 System.out.println("Solicitud para cargar imagen recibida para el producto con ID: " + productId);
		    Product p = productService.uploadProductImage(productId, image);
		    
		    if(p != null) {
		    	return ResponseEntity.ok("perfecto");
		    }else {
		    	System.out.println("hola");	
		    	return null;
		    }
		    
		}
				

	    // Aqui habra que eliminar la imagen
	    // Correcto
	    @DeleteMapping("/delete-product/{publicId}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable String publicId) {
	        productService.deleteProduct(publicId);
	        return ResponseEntity.noContent().build();
	    }

	    //Correcto
	    @PutMapping("/update-product/{publicId}")
	    public ResponseEntity<Product> updateProduct(@PathVariable String publicId, @RequestBody ProductDTO productDetailsDTO) {
	        Product updatedProduct = productService.updateProduct(publicId, productDetailsDTO); 
	        return ResponseEntity.ok(updatedProduct);
	    }
	    
	    
	    @GetMapping("/image/{publicId}")
	    public ResponseEntity<byte[]> getProductImage(@PathVariable String publicId){
			
	    	try {
	            Path imagePath = Paths.get(IMAGE_DIRECTORY + publicId + "_image");
	            byte[] imageBytes = Files.readAllBytes(imagePath);

	            return ResponseEntity.ok()
	                    .contentType(MediaType.IMAGE_JPEG)  // Cambia según el tipo de imagen
	                    .body(imageBytes);
	        } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    }
	    
	    
	
}
