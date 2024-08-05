package com.irojas.demojwt.Auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.Jwt.JwtService;
import com.irojas.demojwt.User.Role;
import com.irojas.demojwt.User.User;
import com.irojas.demojwt.User.UserRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private UserRepository userRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager) {
		super();
		this.userRepository = userRepository;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        
        return new AuthResponse(token);

    }

    public AuthResponse register(RegisterRequest request) {
        
        
        User user = new User();
	    user.setUsername(request.getUsername());
	    user.setPassword(passwordEncoder.encode( request.getPassword()));
	    user.setFirstname(request.getFirstname());
	    user.setLastname(request.getLastname());
	    user.setCountry(request.getCountry());
	    user.setRole(Role.USER);
        

        userRepository.save(user);

               
        return new AuthResponse(jwtService.getToken(user));
        
    }
    
    
    public boolean changePassword(String currentPassword, String newPassword, String token) {
		
    	//Comprobamos el usuario con el token
    	String username = jwtService.getUsernameFromToken(token);
    		
    	Optional<User> optionalUser = userRepository.findByUsername(username);
    	
    	if(optionalUser.isPresent() && jwtService.isTokenValid(token)) {
    		//Usuario existente y token valido
    		    		
    		User user = optionalUser.get();
    		
    		Authentication auth = authenticate(user.getUsername(), user.getPassword());
    		
    		if(auth != null) {
    			user.setPassword(passwordEncoder.encode(newPassword));
        		
        		userRepository.save(user);
        		
        		return true;
    		}
    		return false;
    		
    	}
   	    return false;
    }
    
    public Authentication authenticate(String username, String password) {
		UserDetails userDetails = this.loadUserByUsername(username);
		
		if(userDetails == null) {
			throw new BadCredentialsException("Invalid username or password");
		}
		
		/*
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }*/

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
	}

    
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
		List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
		
		Role rol = userEntity.getRole();
		authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(rol.name().toString())));
		
		return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(),authorityList);
	}
	
	
	public boolean deleteUser(String token) throws UsernameNotFoundException {
		
		if(jwtService.isTokenValid(token)) {
			
			String username = jwtService.getUsernameFromToken(token);
			Optional<User> optionalUser = userRepository.findByUsername(username);
			
			if(optionalUser.isPresent()) {
	    		//Usuario existente y token valido
	    		    		
	    		User user = optionalUser.get();
	    		
	    		userRepository.delete(user);
	    		return true;
	    	}
		}	
		
		return false;
	}
    

}
