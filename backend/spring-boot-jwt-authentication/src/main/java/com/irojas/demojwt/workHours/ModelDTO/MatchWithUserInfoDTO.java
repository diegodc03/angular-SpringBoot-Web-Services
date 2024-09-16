package com.irojas.demojwt.workHours.ModelDTO;

import com.irojas.demojwt.workHours.Model.WorkingRoles;

public class MatchWithUserInfoDTO {
	
	private MatchDTO match;  // Información del partido
    private boolean userWorked;  // Si el usuario trabajó en este partido
    private WorkingRoles role;  // Rol del usuario en el partido (si trabajó)
    private Double payment;  // Pago del usuario en el partido (si trabajó)

    // Constructor vacío
    public MatchWithUserInfoDTO() {}

    // Constructor con parámetros
    public MatchWithUserInfoDTO(MatchDTO match, boolean userWorked, WorkingRoles role, Double payment) {
        this.match = match;
        this.userWorked = userWorked;
        this.role = role;
        this.payment = payment;
    }

    // Getters y setters
    public MatchDTO getMatch() {
        return match;
    }

    public void setMatch(MatchDTO match) {
        this.match = match;
    }

    public boolean isUserWorked() {
        return userWorked;
    }

    public void setUserWorked(boolean userWorked) {
        this.userWorked = userWorked;
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
