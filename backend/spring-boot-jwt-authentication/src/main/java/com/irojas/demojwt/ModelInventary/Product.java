package com.irojas.demojwt.ModelInventary;

import java.util.List;

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

	private Boolean hasSizes; // Indica si el producto tiene varias tallas

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tshirt> tshirts;

    
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

	public Boolean getHasSizes() {
		return hasSizes;
	}

	public void setHasSizes(Boolean hasSizes) {
		this.hasSizes = hasSizes;
	}

	public List<Tshirt> getTshirts() {
		return tshirts;
	}

	public void setTshirts(List<Tshirt> tshirts) {
		this.tshirts = tshirts;
	}
	

	public Product(String name, String description, Double price, Boolean hasSizes, List<Tshirt> tshirts) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.hasSizes = hasSizes;
		this.tshirts = tshirts;
	}

    
    
    
		
		
}
