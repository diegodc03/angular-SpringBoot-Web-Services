package com.irojas.demojwt.Inventay.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.irojas.demojwt.Inventay.ModelDTO.GarmentSaleDTO;

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
@Table(name="productSold", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class ProductSale {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*		RELACION BIDIRECCIONAL SI LA QUISIESE CON SALElIST
     * 	//@ManyToOne
    	//@JoinColumn(name = "product_id")
    	//private Product product;
    
    	@ManyToOne
    	@JoinColumn(name = "sale_list_id", nullable = false) // Asegurarse de que esta columna no permita nulos
    	@JsonBackReference // Evitar recursión infinita
    	private SaleList saleList; // Referencia a SaleList
    */
    
    
    private Integer totalStockSold;
    private Double unitaryPrice;
    private Double totalPrice;
    private String public_id;
    
    private String productId;

    private boolean existanceSizes;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sale_id") // Clave foránea para conectar con ProductSale
    @JsonIgnoreProperties("garmentsSales")
    private List<GarmentSale> garmentsSales = new ArrayList<>();
    
    
    // Getters and Setters




	public Integer getTotalStockSold() {
		return totalStockSold;
	}


	public void setTotalStockSold(Integer totalStockSold) {
		this.totalStockSold = totalStockSold;
	}




	public Double getUnitaryPrice() {
		return unitaryPrice;
	}


	public void setUnitaryPrice(Double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}


	public Double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getPublic_id() {
		return public_id;
	}


	public void setPublic_Id() {
		this.public_id = UUID.randomUUID().toString();
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public boolean isExistanceSizes() {
		return existanceSizes;
	}


	public void setExistanceSizes(boolean existanceSizes) {
		this.existanceSizes = existanceSizes;
	}


	public List<GarmentSale> getGarmentsSales() {
		return garmentsSales;
	}


	public void setGarmentsSales(List<GarmentSale> garmentsSales) {
		this.garmentsSales = garmentsSales;
	}

	
    
	public ProductSale() {
		// TODO Auto-generated constructor stub
	}


	


	public ProductSale(Integer totalStockSold, Double unitaryPrice,
			Double totalPrice, String public_id, String productId, boolean existanceSizes,
			List<GarmentSale> garmentsSales) {
		super();
		this.totalStockSold = totalStockSold;
		this.unitaryPrice = unitaryPrice;
		this.totalPrice = totalPrice;
		this.public_id = public_id;
		this.productId = productId;
		this.existanceSizes = existanceSizes;
		this.garmentsSales = garmentsSales;
	}


	
	
	
	

   
    
}
