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
import com.irojas.demojwt.ModelSaleDTO.SaleListDTO;
import com.irojas.demojwt.RepositoryInventary.GarmentRepository;
import com.irojas.demojwt.RepositoryInventary.ProductRepository;
import com.irojas.demojwt.RepositoryInventary.SaleListRepository;
import com.irojas.demojwt.RepositoryInventary.SaleRepository;

@Service
public class ServiceSale {
	
	private ProductRepository productRepository;
	private SaleRepository saleRepository;
	private SaleListRepository saleListRepository;
	
	
	public ServiceSale (ProductRepository productRepository, SaleRepository saleRepository, SaleListRepository saleListRepository) {
		this.productRepository = productRepository;
		this.saleRepository = saleRepository;
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
	
	
	
	
	
	public SaleList addProduct(@Valid SaleListDTO saleListDTO) {
		
		if(saleListDTO == null) {
			return null;
		}
		
		SaleList completeSale = new SaleList();
		
		completeSale.setCustomerName(saleListDTO.getCustomerName());
		
		for(SaleDTO saleDTO: saleListDTO.getProducts()) {
			
			if(saleDTO == null) {
				return null;
			}
			
			Sale sale = new Sale();
			
			sale.setId_Product(saleDTO.getId_Product());
			//sale.setProduct(saleDTO.getProduct());
			sale.setQuantity(saleDTO.getQuantity());
			sale.setSaleDate(saleDTO.getSaleDate());
			sale.setUnitaryPrice(saleDTO.getUnitaryPrice());
			// total sale price of the t-shirts
			sale.setTotalPrice(saleDTO.getTotalPrice());
			sale.setGarment(saleDTO.getIsGarment());
			
			Optional<Product> optProduct = productRepository.findById(sale.getId_Product());
			
			if(optProduct != null && optProduct.isPresent()) {
				
				Product p = optProduct.get();
			
				if(sale.isGarment() && p.getIsTshirt()) {
					
					List<GarmentSaleDTO> garmentsSales = saleDTO.getGarmentsSales();
		            if (garmentsSales == null || garmentsSales.isEmpty()) {
		                throw new IllegalArgumentException("No se especificaron las tallas para la venta de prendas.");
		            }
					for(GarmentSaleDTO g : garmentsSales) {
						GarmentSale garmentSale = new GarmentSale(g.getSize(), g.getQuantity());	
						
		                // Busca la talla correspondiente en el inventario
		                Garment garment = p.getGarments().stream()
		                        .filter(garm -> garm.getSize() == g.getSize())
		                        .findFirst()
		                        .orElseThrow(() -> new IllegalArgumentException("La talla " + g.getSize() + " no está disponible en el inventario."));

		                if (garment.getStock() < g.getQuantity()) {
		                    throw new IllegalArgumentException("No hay suficiente stock para la talla " + g.getSize());
		                }

		                // Resta la cantidad del stock
		                
		                sale.setQuantity(sale.getQuantity() + g.getQuantity());  
		                garment.setStock(garment.getStock() - g.getQuantity());
		                
		                
		                
		                // Agrega la venta de prendas a la venta
		                sale.getGarmentsSales().add(garmentSale);
					
					}
				}else {
					// Si no es prenda, reduce el stock general
		            if (p.getTotalStock() < sale.getQuantity()) {
		                throw new IllegalArgumentException("No hay suficiente stock para el producto.");
		            }
		            sale.setTotalPrice(saleDTO.getTotalPrice());
					sale.setQuantity(saleDTO.getQuantity());
		            p.setTotalStock(p.getTotalStock() - sale.getQuantity());
				}
			}
			sale.setTotalPrice(sale.getUnitaryPrice() * sale.getQuantity());
			
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
