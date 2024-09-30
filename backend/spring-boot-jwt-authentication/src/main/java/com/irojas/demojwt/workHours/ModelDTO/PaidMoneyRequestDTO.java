package com.irojas.demojwt.workHours.ModelDTO;

public class PaidMoneyRequestDTO {
	private Double moneyPaid;
	private Long seasonId;
	
	
	public Double getMoneyPaid() {
		return moneyPaid;
	}
	public void setMoneyPaid(Double moneyPaid) {
		this.moneyPaid = moneyPaid;
	}
	public Long getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
	}
	
	
	
	public PaidMoneyRequestDTO(Double moneyPaid, Long seasonId) {
		super();
		this.moneyPaid = moneyPaid;
		this.seasonId = seasonId;
	}
	
	public PaidMoneyRequestDTO() {
		super();
	}
	
	
	
}
