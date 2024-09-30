package com.irojas.demojwt.workHours.ModelDTO;

import com.irojas.demojwt.workHours.Model.WorkingRoles;

public class WorkedMatchWithUserInfo extends MatchWithUserInfoDTO{ 
	
	private WorkingRoles role;
	private Double payment;
	
	
	// Constructor vacío
    public WorkedMatchWithUserInfo() {}

    // Constructor que toma un objeto `MatchWithUserInfoDTO`
    public WorkedMatchWithUserInfo(MatchWithUserInfoDTO dto, WorkingRoles role, Double payment) {
        this.setMatch(dto.getMatch());
        this.setUserWorked(dto.isUserWorked());
        this.role = role;
        this.payment = payment;
    }
	
	public WorkingRoles getRole() {
		return role;
	}
	public void setRole(WorkingRoles role) {
		this.role = role;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	
	
	
	
	
}
