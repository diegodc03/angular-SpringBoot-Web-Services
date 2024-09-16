package com.irojas.demojwt.Inventay.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Inventay.ModelDTO.SaleDTO;

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




@Entity
@Table(name="saleList", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class SaleList {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	private String saleId;
	 
	
	private LocalDateTime saleDate;
	
	private double totalAmount;
	
	
	@NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductSale> productsSale = new ArrayList<>();

	
	@ManyToOne
    @JsonManagedReference
	@JsonIgnoreProperties(value="products")
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Nueva relación con User
	
	
	public Long getId() {
		
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId() {
		this.saleId = UUID.randomUUID().toString();
	}
	
	
	public List<ProductSale> getProducts() {
		return productsSale;
	}

	public void setProducts(List<ProductSale> products) {
		this.productsSale = products;
	}
	
	
	public LocalDateTime getSaleDate() {
		return saleDate;
	}

	public void setSaleDate() {
		this.saleDate = LocalDateTime.now();
	}

	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	
	
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// Constructor
	public SaleList() {
		
	}

	public SaleList(@NotNull String saleId, LocalDateTime saleDate, double totalAmount,
			@NotNull List<ProductSale> productsSale) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.totalAmount = totalAmount;
		this.productsSale = productsSale;
	}
	
	
	
	public String getFormattedSaleDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return saleDate != null ? saleDate.format(formatter) : null;
    }

	
	 

	
	
}
