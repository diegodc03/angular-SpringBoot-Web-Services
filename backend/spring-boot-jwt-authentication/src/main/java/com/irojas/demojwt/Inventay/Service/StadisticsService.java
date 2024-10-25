package com.irojas.demojwt.Inventay.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.irojas.demojwt.Inventay.Repository.ProductRepository;
import com.irojas.demojwt.Inventay.Repository.SaleListRepository;

public class StadisticsService {

	
	private ProductRepository productRepository;
	private SaleListRepository saleListRepository;
	
	
	public StadisticsService (ProductRepository productRepository, SaleListRepository saleListRepository) {
		this.productRepository = productRepository;
		this.saleListRepository = saleListRepository;
	}
	
	
	/*
	public List<Map<String, Object>> getMonthlySales() {
        LocalDateTime startOfMonth = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime endOfMonth = LocalDate.now().plusMonths(1).withDayOfMonth(1).atStartOfDay();
        
        return saleListRepository.findAll().stream()
                .collect(Collectors.groupingBy(ps -> ps.getSaleList().getSaleDate().getMonth().toString(), 
                       Collectors.summingDouble(ProductSale::getTotalPrice)))
                .entrySet().stream()
                .map(entry -> Map.of("name", entry.getKey(), "value", entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getMonthlyRevenue() {
        return saleListRepository.findAll().stream()
                .collect(Collectors.groupingBy(sl -> sl.getSaleDate().getMonth().toString(),
                       Collectors.summingDouble(SaleList::getTotalAmount)))
                .entrySet().stream()
                .map(entry -> Map.of("name", entry.getKey(), "value", entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getMonthlyProducts() {
        return saleListRepository.findAll().stream()
                .collect(Collectors.groupingBy(ps -> ps.getSaleList().getSaleDate().getMonth().toString(),
                       Collectors.summingInt(ProductSale::getTotalStockSold)))
                .entrySet().stream()
                .map(entry -> Map.of("name", entry.getKey(), "value", entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getSizeData() {
        return saleListRepository.findAll().stream()
                .flatMap(ps -> ps.getGarmentsSales().stream())
                .collect(Collectors.groupingBy(SizeElementSale::getSize,
                       Collectors.summingInt(SizeElementSale::getStockSold)))
                .entrySet().stream()
                .map(entry -> Map.of("name", entry.getKey().toString(), "value", entry.getValue()))
                .collect(Collectors.toList());
    }
    */
	
}	

