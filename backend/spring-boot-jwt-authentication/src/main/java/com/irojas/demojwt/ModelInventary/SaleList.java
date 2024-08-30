package com.irojas.demojwt.ModelInventary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.irojas.demojwt.ModelSaleDTO.SaleDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;




@Entity
@Table(name="saleList", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class SaleList {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	private String customerName;
	 
	
	private LocalDateTime saleDate;
	
	private double totalAmount;
	
	
	@NotNull
    @OneToMany(mappedBy = "saleList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductSale> productsSale = new ArrayList<>();

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName() {
		this.customerName = UUID.randomUUID().toString();
	}
	
	public List<ProductSale> getProducts() {
		return productsSale;
	}

	public void setProducts(List<ProductSale> products) {
		this.productsSale = products;
	}
	
	
	public LocalDateTime getSaleDate() {
		return saleDate;
	}

	public void setSaleDate() {
		this.saleDate = LocalDateTime.now();
	}

	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	
	
	// Constructor
	public SaleList() {
		
	}

	public SaleList(@NotNull String customerName, LocalDateTime saleDate, double totalAmount,
			@NotNull List<ProductSale> productsSale) {
		super();
		this.customerName = customerName;
		this.saleDate = saleDate;
		this.totalAmount = totalAmount;
		this.productsSale = productsSale;
	}
	
	
	

	
	 

	
	
}
