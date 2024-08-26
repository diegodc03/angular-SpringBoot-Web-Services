package com.irojas.demojwt.ModelSaleDTO;

import javax.validation.constraints.NotNull;
import com.irojas.demojwt.ModelInventary.Size;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class GarmentSaleDTO {
	
	
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

	
	
	public GarmentSaleDTO(@NotNull Size size, int stockSold) {
		super();
		this.size = size;
		this.stockSold = stockSold;
	}

	
	
}
