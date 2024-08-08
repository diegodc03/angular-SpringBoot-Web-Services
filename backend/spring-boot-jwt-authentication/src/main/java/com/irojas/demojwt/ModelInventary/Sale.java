package com.irojas.demojwt.ModelInventary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    
    private LocalDateTime saleDate;

    private Double unitaryPrice;
    
    private Double totalPrice;
    
    private Long id_Product;

    private boolean isGarment;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sale_id") // Asocia la venta con las ventas de ropa
    private List<GarmentSale> garmentsSales = new ArrayList<>();
    
    
    // Getters and Setters
    
    public Long getId() {
    	return id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }
    
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDateTime saleDate) {
		this.saleDate = saleDate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Double getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(Double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	public Long getId_Product() {
		return id_Product;
	}

	public void setId_Product(Long id_Product) {
		this.id_Product = id_Product;
	}
	

	public boolean isGarment() {
		return isGarment;
	}

	public void setGarment(boolean isGarment) {
		this.isGarment = isGarment;
	}

	public List<GarmentSale> getGarmentsSales() {
		return garmentsSales;
	}

	public void setGarmentsSales(List<GarmentSale> garmentsSales) {
		this.garmentsSales = garmentsSales;
	}

	public Sale(Product product, Integer quantity, LocalDateTime saleDate, Double totalPrice, Long id_Product, boolean isGarment) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.saleDate = saleDate;
		this.totalPrice = totalPrice;
		this.id_Product = id_Product;
		this.isGarment = isGarment;
	}

	public Sale() {
		// TODO Auto-generated constructor stub
	}

	
    
	
	

   
    
}
