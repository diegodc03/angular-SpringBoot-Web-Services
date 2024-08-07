package com.irojas.demojwt.ModelInventaryDTO;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDTO {
	@NotNull
	 @Size(min = 1, max = 100)
	 private String name;

	 @Size(max = 255)
	 private String description;

	 @NotNull
	 @DecimalMin("0.0")
	 private Double price;

	 @NotNull
	 private Boolean isTshirt;

	 @NotNull
	 @Min(0)
	 private Integer totalStock;

	 @Valid
	 private List<GarmentDTO> garments;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getIsTshirt() {
		return isTshirt;
	}

	public void setIsTshirt(Boolean isTshirt) {
		this.isTshirt = isTshirt;
	}

	public Integer getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(Integer totalStock) {
		this.totalStock = totalStock;
	}

	public List<GarmentDTO> getGarments() {
		return garments;
	}

	public void setGarments(List<GarmentDTO> garments) {
		this.garments = garments;
	}

	public ProductDTO(@NotNull @Size(min = 1, max = 100) String name, @Size(max = 255) String description,
			@NotNull @DecimalMin("0.0") Double price, @NotNull Boolean isTshirt, @NotNull @Min(0) Integer totalStock,
			@Valid List<GarmentDTO> garments) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.isTshirt = isTshirt;
		this.totalStock = totalStock;
		this.garments = garments;
	}
	
	 
	 
	 
	 
}
