package com.irojas.demojwt.Auth.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.dao.DataIntegrityViolationException;
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
import com.irojas.demojwt.Auth.Model.TemporaryUser;
import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.ModelDTO.AuthResponse;
import com.irojas.demojwt.Auth.ModelDTO.ChangeUserData;
import com.irojas.demojwt.Auth.ModelDTO.LoginRequest;
import com.irojas.demojwt.Auth.ModelDTO.RegisterRequest;
import com.irojas.demojwt.Auth.ModelDTO.UserRegisterDTO;
import com.irojas.demojwt.Auth.Repository.TemporaryUserRepository;
import com.irojas.demojwt.Auth.Repository.UserRepository;
import com.irojas.demojwt.Jwt.JwtService;
import com.irojas.demojwt.sport.Model.Player;
import com.irojas.demojwt.sportRepository.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private UserRepository userRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private TemporaryUserRepository temporaryUserRepository;
    private PlayerRepository playerRepository;
    
    

	public AuthService(UserRepository userRepository, 
			JwtService jwtService, 
			PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, 
			PlayerRepository playerRepository, 
			TemporaryUserRepository temporaryUserRepository) {
		super();
		this.userRepository = userRepository;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.playerRepository = playerRepository;
		this.temporaryUserRepository = temporaryUserRepository;
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
	    
	    Player player = new Player();
	    player.setUser(user);
	    
        
        userRepository.save(user);
        playerRepository.save(player);
        return new AuthResponse(jwtService.getToken(user));
    }
    
    

	
	public AuthResponse confirmUser(String token) {

	    // 1. Buscar el usuario temporal por el token
	    Optional<TemporaryUser> optTempUser = temporaryUserRepository.findTemporaryUserByToken(token);
	    if (!optTempUser.isPresent()) {
	        throw new IllegalArgumentException("Token inválido o usuario no encontrado.");
	    }

	    TemporaryUser tempUser = optTempUser.get();

	    // 2. Crear los roles definitivos
	    List<Role> setRoles = new ArrayList<>();
	    for (Role tempRole : tempUser.getRoles()) {
	        setRoles.add(tempRole);
	    }

	    if (setRoles.isEmpty()) {
	        throw new IllegalArgumentException("Los roles especificados no existen.");
	    }

	    User user = new User();
	    user.setEmail(tempUser.getEmail());
	    user.setPassword( tempUser.getPassword());
	    user.setFirstname(tempUser.getFirstname());
	    user.setLastname(tempUser.getLastname());
	    user.setCountry(tempUser.getCountry());
	    user.setRoles(setRoles);
	    
	    Player player = new Player();
	    player.setUser(user);
	    
	    temporaryUserRepository.delete(tempUser);
	    
	    userRepository.save(user);
	    playerRepository.save(player);
	    
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
    
    
    
    
    

    public boolean changePasswordOut(String token,String newPassword, String confirmPassword) {
		

    	if (newPassword == confirmPassword) {
    		return false;
    	}
    		
    	TemporaryUser tempUser = temporaryUserRepository.findByToken(token)
    			 .orElseThrow(() -> new RuntimeException("token not found"));
    	
    	
    	User user = userRepository.findByEmail(tempUser.getEmail())
   			 .orElseThrow(() -> new RuntimeException("user not found"));
    	
    		
    	Authentication auth = authenticate(user.getUsername(), user.getPassword());
    		
    	if(auth != null) {
    		user.setPassword(passwordEncoder.encode(newPassword));
        	
        	userRepository.save(user);
        	temporaryUserRepository.delete(tempUser);
        	return true;
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
	
	
	
	
	
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    
public TemporaryUser saveTemporaryUser(UserRegisterDTO registerDTO) {
		
		// Crear un set de roles y añadir los roles que quieres asignar
		List<Role> tempRoles = new ArrayList<>();
	    tempRoles.add(Role.USER);
		
		TemporaryUser temporaryUser = new TemporaryUser(registerDTO.getEmail(), registerDTO.getFirstName(), 
				registerDTO.getLastName(), 
				registerDTO.getCountry(), 
				registerDTO.getPassword1(), 
				tempRoles
		);
		
		//Generar un token de confirmación
        String token = UUID.randomUUID().toString();
        temporaryUser.setToken(token);
       
        // change the repositry to use the TemporaryUser Class
     	try {
     		return temporaryUserRepository.save(temporaryUser);
     	} catch (DataIntegrityViolationException e) {
     	    	// Handle the exception (e.g., log it or rethrow it as a custom exception)
     		throw new IllegalStateException("Failed to save temporary user", e);
     	}
}
	
	public int sendMessage(TemporaryUser temporaryUser) {

        String confirmationURL = "http://localhost:8080/auth/confirmation?token=" + temporaryUser.getToken();
        String subject = "Register Confirm";
        String message = "Enter in the next link to confirm the register: "+confirmationURL;
        return sendRegisterEmail(temporaryUser.getEmail(),subject, message);
    }

	public int sendRegisterEmail(String mailReceiver, String subject, String messageToSend) {
	    
	    String sender = "diegodecm03@gmail.com";
	    String mailKey = "epio uzfq mwwh gvaa";  // Clave de la cuenta de Gmail

	    // Configuración de propiedades para la conexión SMTP
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  // El servidor SMTP de Google
	    props.put("mail.smtp.user", sender);            // El usuario
	    props.put("mail.smtp.auth", "true");            // Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587");             // El puerto SMTP seguro de Google

	    // Crear la sesión con autenticación
	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {  // Nombre correcto del método
	            return new PasswordAuthentication(sender, mailKey);
	        }
	    });
	    
	    try {
	        // Crear el mensaje
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(sender));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailReceiver)); // Destinatario
	        message.setSubject(subject);
	        message.setText(messageToSend);

	        // Enviar el mensaje
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", sender, mailKey);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	        return 0; // Éxito
	    } 
	    catch (Exception e) {
	        e.printStackTrace();  
	        return 1; // Error
	    }
	}

	
	

	
	public void deleteTempUser(String token) {
		Optional<TemporaryUser> optTempUser = temporaryUserRepository.findTemporaryUserByToken(token);
		if(optTempUser.isPresent()) {
			TemporaryUser tempUser = optTempUser.get();
			try {
		        temporaryUserRepository.delete(tempUser);
		        
		    } catch (DataIntegrityViolationException e) {
		        throw new IllegalStateException("Failed to save temporary user", e);
		    }
		}
	}
	
	
	
	
	
	public String checkRegisterData(UserRegisterDTO registerDTO) {
        // Check the email
		Optional<TemporaryUser> tempUser = temporaryUserRepository.findByEmail(registerDTO.getEmail());
		
        if (tempUser.isPresent()) {
            return "Email is already in use.";
        }

        int checkedPasswd = registerDTO.checkPasswd();

        if (checkedPasswd != 0) {
            return getPasswordValidationMessage(checkedPasswd);
        }

        // Encode the password
        registerDTO.setPassword1(passwordEncoder.encode( registerDTO.getPassword1()));
        
        return "success";
    }

 
	private String getPasswordValidationMessage(int code) {
        switch (code) {
            case 1:
                return "Password must contain lowercase letters.";
            case 2:
                return "Password must contain uppercase letters.";
            case 3:
                return "Password must contain numbers.";
            case 4:
                return "Password must be at least 8 characters long.";
            case 5:
                return "Passwords do not match.";
            default:
                return "Invalid password.";
        }
    }




	public boolean sendEmailTochangePasswordOut(String email) {
		// TODO Auto-generated method stub
		String token =  UUID.randomUUID().toString();
		String url = "http://localhost:4200/changePasswordOutOfSession?token="+token;
		temporaryUserRepository.save(new TemporaryUser(email,token));
		
		sendRegisterEmail(email, "Cambio de contraseña", url);
		
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	

}
