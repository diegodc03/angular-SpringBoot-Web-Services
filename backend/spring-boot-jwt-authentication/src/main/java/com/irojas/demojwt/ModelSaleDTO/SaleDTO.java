package com.irojas.demojwt.ModelSaleDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.irojas.demojwt.ModelInventary.Garment;
import com.irojas.demojwt.ModelInventary.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class SaleDTO {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    private Long id_Product;

    private Integer quantity;
    
    private LocalDateTime saleDate;

    // price will have the tshirt to public sale
    private Double unitaryPrice;
    
    private Double totalPrice;
    
    private boolean isGarment;
    
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sale_id") // Asocia la venta con las ventas de ropa
    private List<GarmentSaleDTO> garmentsSales = new ArrayList<>();
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDateTime saleDate) {
		this.saleDate = saleDate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(Double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}
	
	public Long getId_Product() {
		return id_Product;
	}

	public void setId_Product(Long id_Product) {
		this.id_Product = id_Product;
	}

	public boolean getIsGarment() {
		return isGarment;
	}

	public void setIsTshirt(boolean isGarment) {
		this.isGarment = isGarment;
	}

	
	public List<GarmentSaleDTO> getGarmentsSales() {
		return garmentsSales;
	}

	public void setGarmentsSales(List<GarmentSaleDTO> garmentsSales) {
		this.garmentsSales = garmentsSales;
	}
	

	public SaleDTO( Integer quantity, LocalDateTime saleDate, Double unitaryPrice, Double totalPrice, Long id_product, boolean isTshirt) {
		super();
		this.quantity = quantity;
		this.saleDate = saleDate;
		this.unitaryPrice = unitaryPrice;
		this.totalPrice = totalPrice;
		this.id_Product = id_product;
		this.isGarment = isTshirt;
	}

	
    
    
    

}
