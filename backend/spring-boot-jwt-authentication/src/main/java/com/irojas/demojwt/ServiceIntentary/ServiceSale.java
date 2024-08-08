package com.irojas.demojwt.ServiceIntentary;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.stereotype.Service;

import com.irojas.demojwt.ModelInventary.Sale;
import com.irojas.demojwt.ModelSaleDTO.GarmentSaleDTO;
import com.irojas.demojwt.ModelSaleDTO.SaleDTO;
import com.irojas.demojwt.RepositoryInventary.GarmentRepository;
import com.irojas.demojwt.RepositoryInventary.ProductRepository;
import com.irojas.demojwt.RepositoryInventary.SaleRepository;

@Service
public class ServiceSale {
	
	private ProductRepository productRepository;
	private GarmentRepository garmentRepository;
	private SaleRepository saleRepository;
	
	
	public ServiceSale (ProductRepository productRepository, GarmentRepository garmentRepository) {
		this.productRepository = productRepository;
		this.garmentRepository = garmentRepository;
	}
	
	
	public List<Sale> getAllSales() {
		List<Sale> saleList = saleRepository.findAll();
		return saleList;
	}
	
	
	public Sale getSaleById(Long id) {
		Optional<Sale> optionalSale = saleRepository.findById(id);
		if(optionalSale != null && optionalSale.isPresent()) {
			return optionalSale.get();
		}
		return null;
	}
	
	
	public Sale addProduct(@Valid SaleDTO saleDTO) {
		
		if(saleDTO == null) {
			return null;
		}
		
		Sale sale = new Sale();
		
		sale.setProduct(saleDTO.getProduct());
		sale.setQuantity(saleDTO.getQuantity());
		sale.setSaleDate(saleDTO.getSaleDate());
		sale.setUnitaryPrice(saleDTO.getUnitaryPrice());
		
		// total sale price of the t-shirts
		sale.setTotalPrice(sale.getQuantity() * sale.getUnitaryPrice());
		
		// si es ropa, va a poder añadir lo que existe
		if(sale.getisGarment()) {
			
			for(GarmentSaleDTO g : saleDTO.getGarmentsSales()) {
				
			}
			
			
		}
		
		
		
		saleRepository.save(sale);
		
		return sale;
	}
	
	
	public Sale updateSale(@Valid Long id, SaleDTO saleDTO) {
		
		if(saleDTO == null) {
			return null;
		}
		
		
		return null;
	}
	
	public Sale deleteSale(Long id) {
		Optional<Sale> optionalSale = saleRepository.findById(id);
		if(optionalSale != null && optionalSale.isPresent()) {
			Sale s = optionalSale.get();
			saleRepository.delete(s);
			return s;
		}
		return null;
		
	}


	
	
	
	
	
	
}
