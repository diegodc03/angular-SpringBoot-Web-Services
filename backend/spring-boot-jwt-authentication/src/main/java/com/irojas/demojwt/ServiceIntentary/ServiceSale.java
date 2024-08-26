package com.irojas.demojwt.ServiceIntentary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.stereotype.Service;

import com.irojas.demojwt.ModelInventary.Garment;
import com.irojas.demojwt.ModelInventary.GarmentSale;
import com.irojas.demojwt.ModelInventary.Product;
import com.irojas.demojwt.ModelInventary.Sale;
import com.irojas.demojwt.ModelInventary.SaleList;
import com.irojas.demojwt.ModelSaleDTO.GarmentSaleDTO;
import com.irojas.demojwt.ModelSaleDTO.SaleDTO;
import com.irojas.demojwt.RepositoryInventary.GarmentRepository;
import com.irojas.demojwt.RepositoryInventary.ProductRepository;
import com.irojas.demojwt.RepositoryInventary.SaleListRepository;
import com.irojas.demojwt.RepositoryInventary.SaleRepository;

@Service
public class ServiceSale {
	
	private ProductRepository productRepository;
	private SaleListRepository saleListRepository;
	
	
	public ServiceSale (ProductRepository productRepository, SaleListRepository saleListRepository) {
		this.productRepository = productRepository;
		this.saleListRepository = saleListRepository;
	}
	
	
	public List<SaleList> getAllSales() {
		List<SaleList> saleList = saleListRepository.findAll();
		return saleList;
	}
	
	
	public SaleList getSaleById(Long id) {
		Optional<SaleList> optionalSale = saleListRepository.findById(id);
		if(optionalSale != null && optionalSale.isPresent()) {
			SaleList s = optionalSale.get();
			return s;
		}
		return null;
	}
	
	
	public List<SaleList> getProductByNumberOfProductsDescending(){
		
		List<SaleList> productsSaleList = this.getAllSales();
		
		if(productsSaleList == null || productsSaleList.isEmpty()) {
			return Collections.emptyList();
		}else {
			// Ordenamos por el tamaño de la lista de productos (productsSale) en orden descendente
	        productsSaleList.sort(Comparator.comparing(saleList -> saleList.getProducts().size(), Comparator.reverseOrder()));
		}
		return productsSaleList;
	}
	
	
	public List<SaleList> getProductByNumberOfProductsAscending(){
		List<SaleList> productsSaleList = this.getAllSales();
		
		if(productsSaleList == null || productsSaleList.isEmpty()) {
			return Collections.emptyList();
		}else {
			// Ordenamos por el tamaño de la lista de productos (productsSale) en orden descendente
	        productsSaleList.sort(Comparator.comparing(saleList -> saleList.getProducts().size()));
		}
		return productsSaleList;
	}
	
	
	
	
	
	public SaleList addProduct(@Valid List<SaleDTO> saleListDTO) {
		
		if(saleListDTO == null) {
			return null;
		}
		
		SaleList completeSale = new SaleList();
		
		// customerName is a UUID
		completeSale.setCustomerName();
		
		// SaleDTO is a product sold, the list is all the products sold in a sale
		for(SaleDTO saleDTO: saleListDTO ) {
			
			if(saleDTO == null) {
				return null;
			}
			
			Sale sale = new Sale();
			sale.setPublic_Id();
			sale.setTotalStockSold(saleDTO.getTotalStockSold());
			sale.setSaleDate(saleDTO.getSaleDate());
			sale.setUnitaryPrice(saleDTO.getUnitaryPrice());
			sale.setTotalPrice(saleDTO.getTotalPrice());
			sale.setExistanceSizes(saleDTO.isExistanceSizes());
			
			 Optional<Product> optProduct = productRepository.findByPublicId(saleDTO.getProductId());
			
			if(optProduct != null && optProduct.isPresent()) {
				
				Product p = optProduct.get();
				
				sale.setProduct(p); // Establece la relación con el producto
			
				// If the product has sizes, it has to delete the parts of sizes, the total stock and the total elements change when it change the sizes
				if(sale.isExistanceSizes() && p.getIsTshirt()) {
					
					List<GarmentSaleDTO> garmentsSales = saleDTO.getGarmentsSales();
		            if (garmentsSales == null || garmentsSales.isEmpty()) {
		                throw new IllegalArgumentException("No se especificaron las tallas para la venta de prendas.");
		            }
		            
		            // loop into the sizes it sold 
					for(GarmentSaleDTO g : garmentsSales) {
						GarmentSale garmentSale = new GarmentSale(g.getSize(), g.getStockSold());	
						
		                // Busca la talla correspondiente en el inventario
						// filter with size
		                Garment garment = p.getGarments().stream()
		                        .filter(garm -> garm.getSize() == g.getSize())
		                        .findFirst()
		                        .orElseThrow(() -> new IllegalArgumentException("La talla " + g.getSize() + " no está disponible en el inventario."));

		                if (garment.getStock() < g.getStockSold()) {
		                    throw new IllegalArgumentException("No hay suficiente stock para la talla " + g.getSize());
		                }

		                // Resta la cantidad del stock
		                
		                //sale.setTotalStockSold(sale.getTotalStockSold() + g.getStockSold());  
		                garment.setStock(garment.getStock() - g.getStockSold());
		                p.setTotalStock(p.getTotalStock() - g.getStockSold());
		                
		                
		                // Agrega la venta de prendas a la venta
		                sale.getGarmentsSales().add(garmentSale);
					
					}
				}else {
					// Si no es prenda, reduce el stock general
		            if (p.getTotalStock() < sale.getTotalStockSold()) {
		                throw new IllegalArgumentException("No hay suficiente stock para el producto.");
		            }
		            sale.setTotalPrice(saleDTO.getTotalPrice());
					sale.setTotalStockSold(saleDTO.getTotalStockSold());
		            p.setTotalStock(p.getTotalStock() - sale.getTotalStockSold());
				}
			}
			
			completeSale.getProducts().add(sale);
			
			
		}
		saleListRepository.save(completeSale);
		return completeSale;	
		
	}
	
	
	public Sale updateSale(@Valid Long id, SaleDTO saleDTO) {
		
		if(saleDTO == null) {
			return null;
		}
		return null;
	}
	
	
	
	public SaleList deleteSale(Long id) {
		Optional<SaleList> optionalSale = saleListRepository.findById(id);
		if(optionalSale != null && optionalSale.isPresent()) {
			SaleList s = optionalSale.get();
			saleListRepository.delete(s);
			return s;
		}
		return null;
	}
}
