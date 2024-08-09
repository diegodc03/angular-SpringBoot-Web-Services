package com.irojas.demojwt.ModelSaleDTO;

import java.util.List;

import javax.validation.constraints.NotNull;

import jakarta.persistence.Entity;


public class SaleListDTO {
	
	
	@NotNull
	private String customerName;
	 
	@NotNull
	private List<SaleDTO> products;

	
	 
	 
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<SaleDTO> getProducts() {
		return products;
	}

	public void setProducts(List<SaleDTO> products) {
		this.products = products;
	}

	public SaleListDTO(@NotNull String customerName, @NotNull List<SaleDTO> products) {
		super();
		this.customerName = customerName;
		this.products = products;
	}
	
	
	
	
	
	
}
