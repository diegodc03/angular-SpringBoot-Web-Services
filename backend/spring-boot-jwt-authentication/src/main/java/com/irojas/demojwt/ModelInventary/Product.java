package com.irojas.demojwt.ModelInventary;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="product", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	private Double price;
	
	private Boolean isShirt; // Indica si el producto tiene varias tallas
	
	private int totalStock;
	

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnoreProperties(value="product")
    private List<Garment> garments;

    
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

	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}

	public Boolean getIsTshirt() {
		return isShirt;
	}

	public void setIsTshirt(Boolean isShirt) {
		this.isShirt = isShirt;
	}


	public List<Garment> getGarments() {
		return garments;
	}

	public void setGarments(List<Garment> garments) {
		this.garments = garments;
	}

	public Product(String name, String description, Double price, Boolean isShirt, List<Garment> garments) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.isShirt = isShirt;
		this.garments = garments;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

    
    
    
		
		
}
