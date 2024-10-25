package com.irojas.demojwt.Auth.ModelDTO;

import lombok.Data;

@Data
public class RequestChangePasswordOutOfSessionEmail {
	
	String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RequestChangePasswordOutOfSessionEmail(String email) {
		super();
		this.email = email;
	}
	
	
	// Constructor sin argumentos
    public RequestChangePasswordOutOfSessionEmail() {
    }
	
	
	
}
