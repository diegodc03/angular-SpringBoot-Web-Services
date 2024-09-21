package com.irojas.demojwt.workHours.ModelDTO;

import com.irojas.demojwt.workHours.Model.Money;
import com.irojas.demojwt.workHours.Model.Season;

public class EarningsDTO {
	
	private SeasonDTO season;
	
	private Double totalMoneyPaid;
	
	private Double moneyToPay;
	
	private Double moneyPaid;

	
	// GETTERS AND SETTERS

	public Double getTotalMoneyPaid() {
		return totalMoneyPaid;
	}

	public SeasonDTO getSeason() {
		return season;
	}

	public void setSeason(SeasonDTO season) {
		this.season = season;
	}

	public void setTotalMoneyPaid(Double totalMoneyPaid) {
		this.totalMoneyPaid = totalMoneyPaid;
	}

	public Double getMoneyToPay() {
		return moneyToPay;
	}

	public void setMoneyToPay(Double moneyToPay) {
		this.moneyToPay = moneyToPay;
	}

	public Double getMoneyPaid() {
		return moneyPaid;
	}

	public void setMoneyPaid(Double moneyPaid) {
		this.moneyPaid = moneyPaid;
	}

	public EarningsDTO(Long id, Double totalMoneyPaid, Double moneyToPay, Double moneyPaid, SeasonDTO season) {
		super();
		this.totalMoneyPaid = totalMoneyPaid;
		this.moneyToPay = moneyToPay;
		this.moneyPaid = moneyPaid;
		this.season = season;
	}
	
	public EarningsDTO() {
		super();
	}
	
	public EarningsDTO(Money money) {
		this.totalMoneyPaid = money.getTotalMoneyPaid();
		this.moneyToPay = money.getMoneyToPay();
		this.moneyPaid = money.getMoneyPaid();
		this.season = new SeasonDTO(money.getSeason());
		
	}
	
	
	
	
	
}
