package com.irojas.demojwt.Auth;

public class ChangeUserData {
	

    String firstname;
    String lastname;
    String country;
    
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	
	public ChangeUserData(String firstname, String lastname, String country) {
		super();

		this.firstname = firstname;
		this.lastname = lastname;
		this.country = country;
	}
    
    
    
    
	
	
	
	
	
	
	
}
