package com.irojas.demojwt.ModelDTO;

import java.util.List;

import com.irojas.demojwt.ModelInventary.ProductSale;

public class SaleListDTO {
	private Long id;
    private String saleId;
    private String saleDate; // Formateada como cadena
    private double totalAmount;
    
    private List<ProductSale> productsSale;
	
    
    
    
    public String getSaleId() {
		return saleId;
	}
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	public String getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<ProductSale> getProductsSale() {
		return productsSale;
	}
	public void setProductsSale(List<ProductSale> productsSale) {
		this.productsSale = productsSale;
	}
    
    
	
	public SaleListDTO() {
		
	}

}
