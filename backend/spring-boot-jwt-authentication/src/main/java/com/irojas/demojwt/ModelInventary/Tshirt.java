package com.irojas.demojwt.ModelInventary;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="tshirt", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Tshirt {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 private String size;
	    
	 private String color;
	    
	 private String material;
	    
	 private Integer stock;

	 @ManyToOne
	 @JoinColumn(name = "product_id")
	 private Product product;

	
	 // Getters y Setters 
	 
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	public Tshirt(String size, String color, String material, Integer stock, Product product) {
		super();
		this.size = size;
		this.color = color;
		this.material = material;
		this.stock = stock;
		this.product = product;
	}


	 
	 	
	
}
