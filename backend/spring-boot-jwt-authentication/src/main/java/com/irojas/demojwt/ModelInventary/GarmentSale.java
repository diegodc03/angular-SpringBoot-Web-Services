package com.irojas.demojwt.ModelInventary;

import javax.validation.constraints.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="garmentSale", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class GarmentSale {
	
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

	
	
	public GarmentSale(Size size, int stockSold) {
		super();
		this.size = size;
		this.stockSold = stockSold;
	}
	
	public GarmentSale() {
		super();
	}
	
}
