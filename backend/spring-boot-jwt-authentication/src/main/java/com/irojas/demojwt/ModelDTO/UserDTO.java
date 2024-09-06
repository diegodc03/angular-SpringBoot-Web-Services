package com.irojas.demojwt.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    int id;
    
	String email;
    String firstname;
    String lastname;
    String country;
    
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	
	
	public UserDTO() {
		super();
	}
	public UserDTO(String email, String firstname, String lastname, String country) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.country = country;
		
	}
	
	
	
}
