package com.irojas.demojwt.ModelInventary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.irojas.demojwt.ModelSaleDTO.GarmentSaleDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Sale {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "product_id")
    //private Product product;
    
    @ManyToOne
    @JoinColumn(name = "sale_list_id")
    @JsonIgnoreProperties("productsSale") // Ignorar referencia inversa en serialización
    private SaleList saleList; // Nueva referencia a la lista de ventas
    
    private Integer totalStockSold;
    
    private LocalDateTime saleDate;

    private Double unitaryPrice;
    
    private Double totalPrice;
    
    private String public_id;
    
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private boolean existanceSizes;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sale_id") // Asocia la venta con las ventas de ropa
    //@JsonManagedReference
    @JsonIgnoreProperties(value="GarmentSale")
    private List<GarmentSale> garmentsSales = new ArrayList<>();
    
    
    // Getters and Setters


	public SaleList getSaleList() {
		return saleList;
	}


	public void setSaleList(SaleList saleList) {
		this.saleList = saleList;
	}


	public Integer getTotalStockSold() {
		return totalStockSold;
	}


	public void setTotalStockSold(Integer totalStockSold) {
		this.totalStockSold = totalStockSold;
	}


	public LocalDateTime getSaleDate() {
		return saleDate;
	}


	public void setSaleDate(LocalDateTime saleDate) {
		this.saleDate = saleDate;
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


	public String getPublic_id() {
		return public_id;
	}


	public void setPublic_Id() {
		this.public_id = UUID.randomUUID().toString();
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public boolean isExistanceSizes() {
		return existanceSizes;
	}


	public void setExistanceSizes(boolean existanceSizes) {
		this.existanceSizes = existanceSizes;
	}


	public List<GarmentSale> getGarmentsSales() {
		return garmentsSales;
	}


	public void setGarmentsSales(List<GarmentSale> garmentsSales) {
		this.garmentsSales = garmentsSales;
	}

	
    
	public Sale() {
		// TODO Auto-generated constructor stub
	}


	


	public Sale(SaleList saleList, Integer totalStockSold, LocalDateTime saleDate, Double unitaryPrice,
			Double totalPrice, String public_id, Product product, boolean existanceSizes,
			List<GarmentSale> garmentsSales) {
		super();
		this.saleList = saleList;
		this.totalStockSold = totalStockSold;
		this.saleDate = saleDate;
		this.unitaryPrice = unitaryPrice;
		this.totalPrice = totalPrice;
		this.public_id = public_id;
		this.product = product;
		this.existanceSizes = existanceSizes;
		this.garmentsSales = garmentsSales;
	}


	
	
	
	

   
    
}
