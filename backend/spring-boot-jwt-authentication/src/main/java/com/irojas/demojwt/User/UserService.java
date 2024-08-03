package com.irojas.demojwt.User;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    
    
    

    public UserService(UserRepository userRepository) {
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
        
        userRepository.updateUser(user.id, user.firstname, user.lastname, user.country);

        return new UserResponse("El usuario se registró satisfactoriamente");
    }

	public UserDTO getUser(Integer id) {
	    User user = userRepository.findById(id).orElse(null);

	    if (user != null) {
	        UserDTO userDTO = new UserDTO();
	        userDTO.setId(user.getId());
	        userDTO.setUsername(user.getUsername());
	        userDTO.setFirstname(user.getFirstname());
	        userDTO.setLastname(user.getLastname());
	        userDTO.setCountry(user.getCountry());
	        return userDTO;
	    }
	    return null;
	}
	
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el usuario en el repositorio
        Optional<User> optionalUser = userRepository.findByUsername(username);

        // Verificar si el usuario está presente
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Retornar el usuario encontrado
        return optionalUser.get();
    }
	

}
