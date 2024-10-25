package com.irojas.demojwt.Auth.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    
	private String token; // Token JWT
    private String message; // Mensaje adicional (opcional)
    
    public AuthResponse(String token) {
        this.token = token;
    }
    
    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
    

	
}
