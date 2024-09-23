package com.irojas.demojwt.workHours.Model;

import com.irojas.demojwt.Auth.Model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity

@Table(name="money")
public class Money {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double totalMoneyPaid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="season_id")
	private Season season;
	
	
	private Double moneyToPay;
	
	private Double moneyPaid;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
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
	
	public Double getTotalMoneyPaid() {
		return totalMoneyPaid;
	}

	public void setTotalMoneyPaid(Double totalMoneyPaid) {
		this.totalMoneyPaid = totalMoneyPaid;
	}

	
	public Money(User user, Season season, Double moneyToPay, Double moneyPaid, Double totalMoneyPaid) {
		super();
		this.user = user;
		this.season = season;
		this.moneyToPay = moneyToPay;
		this.moneyPaid = moneyPaid;
		this.totalMoneyPaid = totalMoneyPaid;
	}

	public Money() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void paid(Double pay) {
		
		if(this.moneyToPay < pay) {
			this.moneyPaid = this.moneyPaid + pay;
			this.moneyToPay = this.moneyToPay - pay;
		}
		
	}
	
	
	
	
	
	
}
