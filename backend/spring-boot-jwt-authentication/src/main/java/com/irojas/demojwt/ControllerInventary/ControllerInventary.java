package com.irojas.demojwt.ControllerInventary;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	

	public ControllerInventary(ProductService productService) {
		super();
		this.productService = productService;
	}


		@GetMapping("all-inventary")
	    public List<Product> getAllProducts() {
			List<Product> allProducts = productService.getAllProducts();
	        return allProducts;
	    }
	    
	    
	    @GetMapping("/inventary/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	        Optional<Product> product = productService.getProdcutById(id);
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
	    
	    
	    //Correcto
	    @PostMapping("/add-product")
	    public Product addProduct(@Valid @RequestBody ProductDTO productDTO) {
	    	Product p = productService.addProduct(productDTO);
	        return p;
	    }

	    // Correcto
	    @DeleteMapping("/delete-product/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return ResponseEntity.noContent().build();
	    }

	    //Correcto
	    @PutMapping("/update-product/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDetailsDTO) {
	        Product updatedProduct = productService.updateProduct(id, productDetailsDTO);
	        return ResponseEntity.ok(updatedProduct);
	    }
	
	
	
	
	
	
	
	
	
}
