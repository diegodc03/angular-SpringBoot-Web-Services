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
import com.irojas.demojwt.ModelInventary.Sale;
import com.irojas.demojwt.ModelInventaryDTO.ProductDTO;
import com.irojas.demojwt.ModelSaleDTO.SaleDTO;
import com.irojas.demojwt.ServiceIntentary.ProductService;
import com.irojas.demojwt.ServiceIntentary.ServiceSale;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class ControllerSale {
	
	
	private ServiceSale saleService;
	

	public ControllerSale(ServiceSale saleService) {
		super();
		this.saleService = saleService;
	}


		@GetMapping("all-sales")
	    public List<Sale> getAllProducts() {
			List<Sale> allProducts = saleService.getAllSales();
	        return allProducts;
	    }
	    
	    /*
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
	    */

	    @PostMapping("/add-sale")
	    public Sale addProduct(@Valid @RequestBody SaleDTO saleDTO) {
	    	Sale s = saleService.addProduct(saleDTO);
	        return s;
	    }

	    @DeleteMapping("/delete-sale/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        saleService.deleteSale(id);
	        return ResponseEntity.noContent().build();
	    }

	    @PutMapping("/update-sale/{id}")
	    public ResponseEntity<Sale> updateProduct(@PathVariable Long id, @RequestBody SaleDTO saleDTO) {
	        Sale updatedSale = saleService.updateSale(id, saleDTO);
	        return ResponseEntity.ok(updatedSale);
	    }
	
	
	
	
	
	

}
