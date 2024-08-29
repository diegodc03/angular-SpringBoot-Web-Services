package com.irojas.demojwt.ControllerInventary;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

import com.irojas.demojwt.ServiceIntentary.ServiceSale;

public class ControllerStadistics {
	
	
	private ServiceSale saleService;
	

	public ControllerStadistics(ServiceSale saleService) {
		super();
		this.saleService = saleService;
	}
	/*
	// Ventas mensuales
	@GetMapping("/sales/monthly")
    public List<Map<String, Object>> getMonthlySales() {
        return saleService.getMonthlySales();
    }
	
	// Ganancia mensual
    @GetMapping("/revenue/monthly")
    public List<Map<String, Object>> getMonthlyRevenue() {
        return saleService.getMonthlyRevenue();
    }
    
    // Productos Vendidos Mensualemente
    @GetMapping("/products/monthly")
    public List<Map<String, Object>> getMonthlyProducts() {
        return saleService.getMonthlyProducts();
    }

    
    // Tallas mas vendidas
    @GetMapping("/sizes")
    public List<Map<String, Object>> getSizeData() {
        return saleService.getSizeData();
    }*/
	
}
