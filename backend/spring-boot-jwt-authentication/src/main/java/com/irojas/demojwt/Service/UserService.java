package com.irojas.demojwt.Service;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.Jwt.JwtService;
import com.irojas.demojwt.Model.Role;
import com.irojas.demojwt.Model.User;
import com.irojas.demojwt.ModelDTO.UserDTO;
import com.irojas.demojwt.ModelDTO.UserRequest;
import com.irojas.demojwt.ModelDTO.UserResponse;
import com.irojas.demojwt.Repsository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {


    private UserRepository userRepository;
    
    

	public UserService( UserRepository userRepository) {
		super();

		this.userRepository = userRepository;
	}

	@Transactional
    public UserResponse updateUser(UserRequest userRequest) {
       
		User user = new User();
	    user.setId(userRequest.getId());
	    user.setFirstname(userRequest.getFirstname());
	    user.setLastname(userRequest.getLastname());
	    user.setCountry(userRequest.getCountry());
	    user.setRole(Role.USER);
        
        userRepository.updateUser(user.getId(), user.getFirstname(), user.getLastname(), user.getCountry());

        return new UserResponse("El usuario se registró satisfactoriamente");
    }

	public UserDTO getUser(Integer id) {
	    User user = userRepository.findById(id).orElse(null);

	    if (user != null) {
	        UserDTO userDTO = new UserDTO();
	        userDTO.setId(user.getId());
	        userDTO.setEmail(user.getEmail());
	        userDTO.setFirstname(user.getFirstname());
	        userDTO.setLastname(user.getLastname());
	        userDTO.setCountry(user.getCountry());
	        return userDTO;
	    }
	    return null;
	}
	
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscar el usuario en el repositorio
        Optional<User> optionalUser = userRepository.findByEmail(email);

        // Verificar si el usuario está presente
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }

        // Retornar el usuario encontrado
        return optionalUser.get();
    }

	
	public UserDTO getUserData(String email) {
        
        // Encuentra al usuario por su email
        Optional<User> optUser = userRepository.findByEmail(email);
        if (optUser.isPresent()) {
            User user = optUser.get();
            // Convierte el usuario a UserDTO
            return new UserDTO(user.getEmail(), user.getFirstname(), user.getLastname(), user.getCountry());
        }
        // Retorna null si el usuario no existe
        return null;
    }
	
	
	
	
	
	

}
