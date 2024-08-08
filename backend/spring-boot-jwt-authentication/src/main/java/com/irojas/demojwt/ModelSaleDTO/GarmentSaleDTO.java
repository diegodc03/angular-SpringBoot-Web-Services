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
	
	private int quantity;

	
	
	
	
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
	public GarmentSaleDTO(@NotNull Size size, int quantity) {
		super();
		this.size = size;
		this.quantity = quantity;
	}

	
	
}
