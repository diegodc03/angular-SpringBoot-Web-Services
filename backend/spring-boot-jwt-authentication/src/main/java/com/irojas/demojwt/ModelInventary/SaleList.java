package com.irojas.demojwt.ModelInventary;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.irojas.demojwt.ModelSaleDTO.SaleDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.UUID;



@Entity
@Table(name="saleList", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class SaleList {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	private String customerName;
	 
	@NotNull
	// CascadeType.ALL -----> permite manejar la venta desde esta entidad, es decir, se añade automaticamente
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Sale> productsSale = new ArrayList<>();

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
	
	public List<Sale> getProducts() {
		return productsSale;
	}

	public void setProducts(List<Sale> products) {
		this.productsSale = products;
	}

	public SaleList(@NotNull String customerName, @NotNull List<Sale> productsSale) {
		super();
		this.customerName = customerName;
		this.productsSale = productsSale;
	}
	
	public SaleList() {
		
	}

	
	 

	
	
}
