package com.irojas.demojwt.ServiceIntentary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;


import org.springframework.stereotype.Service;

import com.irojas.demojwt.Jwt.JwtTokenManager;
import com.irojas.demojwt.Model.User;
import com.irojas.demojwt.ModelDTO.SaleListDTO;
import com.irojas.demojwt.ModelInventary.Garment;
import com.irojas.demojwt.ModelInventary.GarmentSale;
import com.irojas.demojwt.ModelInventary.Product;
import com.irojas.demojwt.ModelInventary.ProductSale;
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
	private JwtTokenManager jwtTokenManager;
	
	public ServiceSale (ProductRepository productRepository, SaleListRepository saleListRepository, JwtTokenManager jwtTokenManager) {
		this.productRepository = productRepository;
		this.saleListRepository = saleListRepository;
		this.jwtTokenManager = jwtTokenManager;
	}
	
	
	public List<SaleList> getAllSales() {
		List<SaleList> saleList = saleListRepository.findAll();
		return saleList;
	}
	
	
	public List<SaleList> getSalesByUserEmail(String email){
		Optional<List<SaleList>> optionalSaleList = saleListRepository.findSaleListByUserEmail(email);
		if(optionalSaleList.isPresent()) {
        	return optionalSaleList.get();
        }
        return null;
	}
	
	
	public SaleList getSaleById(Long id) {
		Optional<SaleList> optionalSale = saleListRepository.findById(id);
		if(optionalSale != null && optionalSale.isPresent()) {
			SaleList s = optionalSale.get();
			return s;
		}
		return null;
	}
	
	
	public SaleList getSaleBySaleId(String saleId) {
		Optional<SaleList> optSale = saleListRepository.findBySaleId(saleId);
		if(optSale.isPresent()) {
			SaleList sale = optSale.get();
			return sale;
		}
		return null;
	}
	
	
	public List<SaleList> getProductByNumberOfProductsDescending(String email){
		
		List<SaleList> productsSaleList = this.getSalesByUserEmail(email);
		
		if(productsSaleList == null || productsSaleList.isEmpty()) {
			return Collections.emptyList();
		}else {
			// Ordenamos por el tamaño de la lista de productos (productsSale) en orden descendente
	        productsSaleList.sort(Comparator.comparing(saleList -> saleList.getProducts().size(), Comparator.reverseOrder()));
		}
		return productsSaleList;
	}
	
	
	public List<SaleList> getProductByNumberOfProductsAscending(String email){
		List<SaleList> productsSaleList = this.getSalesByUserEmail(email);
		
		if(productsSaleList == null || productsSaleList.isEmpty()) {
			return Collections.emptyList();
		}else {
			// Ordenamos por el tamaño de la lista de productos (productsSale) en orden descendente
	        productsSaleList.sort(Comparator.comparing(saleList -> saleList.getProducts().size()));
		}
		return productsSaleList;
	}
	
	
	
	
	
	public SaleList addProduct(@Valid List<SaleDTO> saleListDTO, String token) {
		
		if(saleListDTO == null) {
			return null;
		}
		
		User user = this.jwtTokenManager.getUserFromToken(token);
		
		SaleList completeSale = new SaleList();
		
		// customerName is a UUID
		completeSale.setSaleId();
		completeSale.setSaleDate();
		
		// SaleDTO is a product sold, the list is all the products sold in a sale
		for(SaleDTO saleDTO: saleListDTO ) {
			
			if(saleDTO == null) {
				return null;
			}
			
			ProductSale productSale = new ProductSale();
			productSale.setPublic_Id();
			productSale.setTotalStockSold(saleDTO.getTotalStockSold());
			productSale.setUnitaryPrice(saleDTO.getUnitaryPrice());
			productSale.setTotalPrice(saleDTO.getTotalPrice());
			productSale.setExistanceSizes(saleDTO.isExistanceSizes());
			//productSale.setProductId(saleDTO.getProductId());		
			

			
			completeSale.setTotalAmount(completeSale.getTotalAmount() + saleDTO.getTotalPrice());
			
			 Optional<Product> optProduct = productRepository.findByPublicId(saleDTO.getProductId());
			
			if(optProduct != null && optProduct.isPresent()) {
				
				Product p = optProduct.get();
				
				productSale.setProductId(p.getPublicId());
			
				// If the product has sizes, it has to delete the parts of sizes, the total stock and the total elements change when it change the sizes
				if(productSale.isExistanceSizes() && p.getIsTshirt()) {
					
					List<GarmentSaleDTO> garmentsSales = saleDTO.getGarmentsSales();
		            if (garmentsSales == null || garmentsSales.isEmpty()) {
		                throw new IllegalArgumentException("No se especificaron las tallas para la venta de prendas.");
		            }
		            
		            // loop into the sizes it sold 
		            for (GarmentSaleDTO g : garmentsSales) {
	                    Garment garment = p.getGarments().stream()
	                        .filter(garm -> garm.getSize() == g.getSize())
	                        .findFirst()
	                        .orElseThrow(() -> new IllegalArgumentException("La talla " + g.getSize() + " no está disponible en el inventario."));

	                    if (garment.getStock() < g.getStockSold()) {
	                        throw new IllegalArgumentException("No hay suficiente stock para la talla " + g.getSize());
	                    }

	                    garment.setStock(garment.getStock() - g.getStockSold());
	                    p.setTotalStock(p.getTotalStock() - g.getStockSold());

	                    GarmentSale garmentSale = new GarmentSale(g.getSize(), g.getStockSold());
	                    productSale.getGarmentsSales().add(garmentSale);
	                }
				}else {
					
					// Si no es prenda, reduce el stock general
		            if (p.getTotalStock() < productSale.getTotalStockSold()) {
		                throw new IllegalArgumentException("No hay suficiente stock para el producto.");
		            }
		            productSale.setTotalPrice(saleDTO.getTotalPrice());
					productSale.setTotalStockSold(saleDTO.getTotalStockSold());
		            p.setTotalStock(p.getTotalStock() - productSale.getTotalStockSold());
				}
			}
			
			
			//add the productos in the array
			completeSale.getProducts().add(productSale);
			
		}
		completeSale.setUser(user);
		saleListRepository.save(completeSale);
		return completeSale;	
		
	}
	
	
	public ProductSale updateSale(@Valid Long id, SaleDTO saleDTO) {
		
		if(saleDTO == null) {
			return null;
		}
		return null;
	}
	
	
	
	public SaleList deleteSale(String id) {
		Optional<SaleList> optionalSale = saleListRepository.findBySaleId(id);
		if(optionalSale != null && optionalSale.isPresent()) {
			SaleList s = optionalSale.get();
			saleListRepository.delete(s);
			return s;
		}
		return null;
	}
	
	
	public SaleList deleteProductSale(SaleList sale, String publicId) {
		
		List<ProductSale> products = sale.getProducts();
    	
		if(products.size() == 1) {
			this.saleListRepository.delete(sale);
			return null;
		}
		
    	Optional<ProductSale> optionalProductSold = products.stream().filter(product -> product.getPublic_id().equals(publicId)).findFirst();
    
    	if(optionalProductSold.isPresent()) {
    		ProductSale productSold = optionalProductSold.get();
    		
    		sale.setTotalAmount(sale.getTotalAmount()-productSold.getTotalPrice());
    		
    		
    		if(products.remove(productSold)) {
    			//Producto Eliminado
    			sale.setProducts(products);
    			this.saleListRepository.save(sale);
    			return sale;
    			
    		}
    	}
    	return null;
	}
}
