package com.irojas.demojwt.Inventay.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.irojas.demojwt.Auth.Model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	private String publicId; // Nuevo campo para el identificador externo
	
	private String name;
	
	private String description;
	
	private Double price;
	
	private Boolean isTshirt; // Indica si el producto tiene varias tallas
	
	private int totalStock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value="product")
    private List<Garment> garments;

    private String imageName;
    
    
    @ManyToOne
    @JsonManagedReference
	@JsonIgnoreProperties(value="products")
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Nueva relación con User
    
    // Gatters and Setters
    
	public String getPublicId() {
		return publicId;
	}

	public void setPublicId() {
		this.publicId =  UUID.randomUUID().toString();
	}

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
		return isTshirt;
	}

	public void setIsTshirt(Boolean isTshirt) {
		this.isTshirt = isTshirt;
	}


	public List<Garment> getGarments() {
		return garments;
	}

	public void setGarments(List<Garment> garments) {
		this.garments = garments;
	}
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	
	
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product(String publicId, String name, String description, Double price, Boolean isTshirt, List<Garment> garments) {
		super();	
		this.publicId = publicId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.isTshirt = isTshirt;
		this.garments = garments;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}
	
    
		
		
}
