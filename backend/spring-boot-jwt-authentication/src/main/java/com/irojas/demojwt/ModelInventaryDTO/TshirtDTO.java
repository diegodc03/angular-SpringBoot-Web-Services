package com.irojas.demojwt.ModelInventaryDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TshirtDTO {
	 @NotNull
	 @Size(min = 1, max = 100)
	 private String size;

	 @NotNull
	 @Size(min = 1, max = 50)
	 private String color;

	 @NotNull
	 @Size(min = 1, max = 50)
	 private String material;

	 @NotNull
	 @Min(0)
	 private Integer stock;

	 
	 
	 
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	
	
	public TshirtDTO(@NotNull @Size(min = 1, max = 100) String size, @NotNull @Size(min = 1, max = 50) String color,
			@NotNull @Size(min = 1, max = 50) String material, @NotNull @Min(0) Integer stock) {
		super();
		this.size = size;
		this.color = color;
		this.material = material;
		this.stock = stock;
	}

	 
	
	
	


}