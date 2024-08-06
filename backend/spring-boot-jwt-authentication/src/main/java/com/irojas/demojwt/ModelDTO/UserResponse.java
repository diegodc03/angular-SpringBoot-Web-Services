package com.irojas.demojwt.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
   
	public UserResponse(String string) {
		this.message = string;
	}

	String message;
}
