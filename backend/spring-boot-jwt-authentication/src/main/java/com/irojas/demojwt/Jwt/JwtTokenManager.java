package com.irojas.demojwt.Jwt;

import org.springframework.stereotype.Service;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.ModelDTO.UserDTO;
import com.irojas.demojwt.Auth.Service.UserService;

@Service
public class JwtTokenManager {
	private final JwtService jwtService;
    private final UserService userService;


    public JwtTokenManager(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public User getUserFromToken(String token) {
        String email = jwtService.getEmaileFromToken(token);
        return userService.getUserData(email); // Llamada al UserService para obtener datos del usuario
    }
    
    public String getEmailFromToken(String token) {
    	return jwtService.getEmaileFromToken(token);
    }
}
