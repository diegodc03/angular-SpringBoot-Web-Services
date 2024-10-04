package com.irojas.demojwt.Auth.Service;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.Auth.Model.Role;
import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.ModelDTO.UserDTO;
import com.irojas.demojwt.Auth.ModelDTO.UserRequest;
import com.irojas.demojwt.Auth.ModelDTO.UserResponse;
import com.irojas.demojwt.Auth.Repository.UserRepository;
import com.irojas.demojwt.Jwt.JwtService;

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
	    
	    ArrayList<Role> roles = new ArrayList<>();
	    roles.add(Role.USER);
	    user.setRoles(roles);
        
        userRepository.updateUser(user.getId(), user.getFirstname(), user.getLastname(), user.getCountry());

        return new UserResponse("El usuario se registró satisfactoriamente");
    }

	public UserDTO getUser(Long id) {
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

	
	public User getUserData(String email) {
        
        // Encuentra al usuario por su email
        Optional<User> optUser = userRepository.findByEmail(email);
        if (optUser.isPresent()) {
            return optUser.get();
            
        }
        // Retorna null si el usuario no existe
        return null;
    }
	
	
	
	// Añadir rol PLAYER al usuario
    public void addPlayerRole(String email) throws Exception {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new Exception("Usuario no encontrado"));
        
        if (!user.getRoles().contains(Role.PLAYER)) {
            user.getRoles().add(Role.PLAYER);  // Añadir el rol PLAYER
            userRepository.save(user);  // Guardar cambios en la base de datos
        } else {
            throw new Exception("El usuario ya tiene el rol PLAYER.");
        }
    }

    // Eliminar rol PLAYER del usuario
    public void removePlayerRole(String email) throws Exception {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new Exception("Usuario no encontrado"));
        
        if (user.getRoles().contains(Role.PLAYER)) {
            user.getRoles().remove(Role.PLAYER);  // Eliminar el rol PLAYER
            userRepository.save(user);  // Guardar cambios en la base de datos
        } else {
            throw new Exception("El usuario no tiene el rol PLAYER.");
        }
    }
	
	
	
	
	
	

}
