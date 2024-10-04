package com.irojas.demojwt.Auth.Service;

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

import com.irojas.demojwt.Auth.Model.Role;
import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.ModelDTO.AuthResponse;
import com.irojas.demojwt.Auth.ModelDTO.ChangeUserData;
import com.irojas.demojwt.Auth.ModelDTO.LoginRequest;
import com.irojas.demojwt.Auth.ModelDTO.RegisterRequest;
import com.irojas.demojwt.Auth.Repository.UserRepository;
import com.irojas.demojwt.Jwt.JwtService;

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
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user=userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token=jwtService.getToken(user);
        
        return new AuthResponse(token);
    }

    public AuthResponse register(RegisterRequest request) {
                
        User user = new User();
	    user.setEmail(request.getEmail());
	    user.setPassword(passwordEncoder.encode( request.getPassword()));
	    user.setFirstname(request.getFirstname());
	    user.setLastname(request.getLastname());
	    user.setCountry(request.getCountry());
	    
	    ArrayList<Role> roles = new ArrayList<>();
	    roles.add(Role.USER);
	    user.setRoles(roles);
	    
	    
        
        userRepository.save(user);

        return new AuthResponse(jwtService.getToken(user));
    }
    
    
    public boolean changePassword(String currentPassword, String newPassword, String token) {
		
    	//Comprobamos el usuario con el token
    	String email = jwtService.getEmaileFromToken(token);
    		
    	Optional<User> optionalUser = userRepository.findByEmail(email);
    	
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

    
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        User userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Crear lista de autoridades (roles)
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        // Iterar sobre los roles del usuario y agregarlos a la lista de autoridades
        for (Role role : userEntity.getRoles()) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        }

        // Devolver un UserDetails con username, password y las autoridades (roles)
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), authorityList);
    }

	
	
	public boolean deleteUser(String token) throws UsernameNotFoundException {
		
		if(jwtService.isTokenValid(token)) {
			
			String email = jwtService.getEmaileFromToken(token);
			Optional<User> optionalUser = userRepository.findByEmail(email);
			
			if(optionalUser.isPresent()) {
	    		//Usuario existente y token valido
	    		    		
	    		User user = optionalUser.get();
	    		
	    		userRepository.delete(user);
	    		return true;
	    	}
		}	
		return false;
	}
    
	
	public void changeUserData(String token, ChangeUserData changeData) {
		
		if (token == null || changeData == null) {
	        throw new IllegalArgumentException("Token and change data must not be null");
	    }

	    if (!jwtService.isTokenValid(token)) {
	        throw new SecurityException("Invalid token");
	    }
		
		if(jwtService.isTokenValid(token)) {
			
			String email = jwtService.getEmaileFromToken(token);
			Optional<User> optionalUser = userRepository.findByEmail(email);
			
			if(optionalUser.isPresent()) {
	    		//Usuario existente y token valido
	    		    		
	    		User user = optionalUser.get();
	    		
	    		if(!changeData.getCountry().isBlank()) {
	    			user.setCountry(changeData.getCountry());
	    		}
	    		if(!changeData.getFirstname().isBlank()) {
	    			user.setFirstname(changeData.getFirstname());
	    		}
	    		if(!changeData.getLastname().isBlank()) {
	    			user.setLastname(changeData.getLastname());
	    		}
	    		
	    		userRepository.save(user);
	    	}
		}
	}
	
	
	public void sendRegisterMessage() {
		
	}
	
	

}
