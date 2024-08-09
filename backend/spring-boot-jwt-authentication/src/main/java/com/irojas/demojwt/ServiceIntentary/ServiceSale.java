package com.irojas.demojwt.ServiceIntentary;

import java.util.ArrayList;
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
			sale.setTotalPrice(sale.getQuantity() * sale.getUnitaryPrice());
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
		                garment.setStock(garment.getStock() - g.getQuantity());
		                
		                // Agrega la venta de prendas a la venta
		                sale.getGarmentsSales().add(garmentSale);
					
					}
				}else {
					// Si no es prenda, reduce el stock general
		            if (p.getTotalStock() < sale.getQuantity()) {
		                throw new IllegalArgumentException("No hay suficiente stock para el producto.");
		            }
		            p.setTotalStock(p.getTotalStock() - sale.getQuantity());
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
