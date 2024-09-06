package com.irojas.demojwt.ModelSaleDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.irojas.demojwt.ModelInventary.Garment;
import com.irojas.demojwt.ModelInventary.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class SaleDTO {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "product_id")
    //private Product product;
    
    private String productId;
    
    private String public_id;

    private String name;
    
 // price will have the tshirt to public sale
    private Double unitaryPrice;
    
    private Double totalPrice;
    
    
    private Integer totalStockSold;
    
    private String imageName;
    
    private LocalDateTime saleDate;

    private boolean existanceSizes;
    
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sale_id") // Asocia la venta con las ventas de ropa
    private List<GarmentSaleDTO> garmentsSales = new ArrayList<>();


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}

	
	

	public String getPublic_id() {
		return public_id;
	}


	public void setPublic_id(String public_id) {
		this.public_id = public_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Double getUnitaryPrice() {
		return unitaryPrice;
	}


	public void setUnitaryPrice(Double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}


	public Double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Integer getTotalStockSold() {
		return totalStockSold;
	}


	public void setTotalStockSold(Integer totalStockSold) {
		this.totalStockSold = totalStockSold;
	}


	public String getImageName() {
		return imageName;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	public LocalDateTime getSaleDate() {
		return saleDate;
	}


	public void setSaleDate(LocalDateTime saleDate) {
		this.saleDate = saleDate;
	}


	public boolean isExistanceSizes() {
		return existanceSizes;
	}


	public void setExistanceSizes(boolean existanceSizes) {
		this.existanceSizes = existanceSizes;
	}


	public List<GarmentSaleDTO> getGarmentsSales() {
		return garmentsSales;
	}


	public void setGarmentsSales(List<GarmentSaleDTO> garmentsSales) {
		this.garmentsSales = garmentsSales;
	}


	public SaleDTO(String productId,String public_id, String name, Double unitaryPrice, Double totalPrice, Integer totalStockSold,
			String imageName, boolean existanceSizes, List<GarmentSaleDTO> garmentsSales) {
		super();
		this.productId = productId;
		this.public_id = public_id;
		this.name = name;
		this.unitaryPrice = unitaryPrice;
		this.totalPrice = totalPrice;
		this.totalStockSold = totalStockSold;
		this.imageName = imageName;
		this.existanceSizes = existanceSizes;
		this.garmentsSales = garmentsSales;
	}
    
    

    
    
    
    
    
	

	
	
    
    
    

}
