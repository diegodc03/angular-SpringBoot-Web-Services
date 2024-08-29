package com.irojas.demojwt.ControllerInventary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.irojas.demojwt.ModelInventary.Garment;
import com.irojas.demojwt.ModelInventary.Product;
import com.irojas.demojwt.ModelInventaryDTO.ProductDTO;
import com.irojas.demojwt.ServiceIntentary.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventary")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class ControllerInventary {

	private ProductService productService;
	private static String IMAGE_DIRECTORY = "src/main/resources/static/uploads/";

	public ControllerInventary(ProductService productService) {
		super();
		this.productService = productService;
	}


		@GetMapping("all-inventary")
	    public List<Product> getAllProducts() {
			List<Product> allProducts = productService.getAllProducts();
			
	        return allProducts;
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
	    public List<Product> getProductsByPriceAscending() {
	        return productService.getProductByPriceAscending();
	    }
	    
	    
	    @GetMapping("/price-descending")
	    public List<Product> getProductsByPriceDescending() {
	        return productService.getProductByPriceDescending();
	    }
	    
	    @GetMapping("/stock-ascending")
	    public List<Product> getProductsByStockAscending() {
	        return productService.getProductByStockAscending();
	    }
	    
	    @GetMapping("/stock-descending")
	    public List<Product> getProductsByStockDescending() {
	        return productService.getProductByStockDescending();
	    }
	    
	    
	    
	    @PostMapping("/add-product-info")
	    public ResponseEntity<String> addProduct(
	            @RequestPart("productDTO") String productDTO,
	            @RequestPart("file") MultipartFile file) throws IOException {

	        System.out.println("ProductDTO: " + productDTO);
	        System.out.println("File: " + file.getOriginalFilename());

	        // Here you can deserialize productDTO to a Product object if needed
	        // Product product = new ObjectMapper().readValue(productDTO, Product.class);

	        // Handle your logic here, such as saving the product and file

	        return ResponseEntity.ok("Product added successfully");
	    }

	    
	    
	    /*
	    //Correcto
	    @PostMapping("/add-product-info")
	    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) {
	    	
	        Product product = productService.addProduct(productDTO);
	   
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
		*/
		
	    
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
