package com.irojas.demojwt.workHours.ModelDTO;

public class WorkingRoleDTO {
	
	private String role;
	private Double salary;
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
	
	public WorkingRoleDTO(String role, Double salary) {
		super();
		this.role = role;
		this.salary = salary;
	}
	
	
	public WorkingRoleDTO() {
		super();
	}
	
	
	
	
	
	
}
