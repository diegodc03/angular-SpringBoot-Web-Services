package com.irojas.demojwt.Inventay.ModelDTO;

import javax.validation.constraints.NotNull;

import com.irojas.demojwt.Inventay.Model.Size;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class SizeElementSaleDTO {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private Size size;
	
	private int stockSold;

	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Size getSize() {
		return size;
	}	
	
	public void setSize(Size size) {
		this.size = size;
	}

	public int getStockSold() {
		return stockSold;
	}

	public void setStockSold(int stockSold) {
		this.stockSold = stockSold;
	}

	
	
	public SizeElementSaleDTO(@NotNull Size size, int stockSold) {
		super();
		this.size = size;
		this.stockSold = stockSold;
	}

	
	
}
