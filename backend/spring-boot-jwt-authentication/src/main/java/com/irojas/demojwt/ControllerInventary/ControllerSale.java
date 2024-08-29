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

import com.irojas.demojwt.ModelInventary.ProductSale;
import com.irojas.demojwt.ModelInventary.SaleList;
import com.irojas.demojwt.ModelSaleDTO.SaleDTO;
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
	    public List<SaleList> getAllProducts() {
			List<SaleList> allProducts = saleService.getAllSales();
	        return allProducts;
	    }
	    
		
	    @GetMapping("/{id}")
	    public ResponseEntity<SaleList> getProductById(@PathVariable Long id) {
	        SaleList product = saleService.getSaleById(id);
	        return ResponseEntity.ok(product);
	    }
	    
	    
	    @GetMapping("/number-products-sold-ascending")
	    public List<SaleList> getProductsByPriceAscending() {
	        return saleService.getProductByNumberOfProductsAscending();
	    }
	    
	    
	    @GetMapping("/number-products-sold-descending")
	    public List<SaleList> getProductsByPriceDescending() {
	        return saleService.getProductByNumberOfProductsDescending();
	    }
	    
	    /*
	    @GetMapping("/stock-ascending")
	    public List<Product> getProductsByStockAscending() {
	        return productService.getProductByStockAscending();
	    }
	    
	    @GetMapping("/stock-descending")
	    public List<Product> getProductsByStockDescending() {
	        return productService.getProductByStockDescending();
	    }
	    */
		
	    
	    
		//Darle al boton de añadir cualquier producto
	    @PostMapping("/add-sale")
	    public String addProduct(@Valid @RequestBody List<SaleDTO> saleDTO) {
	    	SaleList s = saleService.addProduct(saleDTO);
	        return "hola";
	    }

	    
	    // Esto todavia no está
	    @DeleteMapping("/delete-sale/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        saleService.deleteSale(id);
	        return ResponseEntity.noContent().build();
	    }

	    
	    @PutMapping("/update-sale/{id}")
	    public ResponseEntity<ProductSale> updateProduct(@PathVariable Long id, @RequestBody SaleDTO saleDTO) {
	        ProductSale updatedSale = saleService.updateSale(id, saleDTO);
	        return ResponseEntity.ok(updatedSale);
	    }
	    
	    
	    
	
}
