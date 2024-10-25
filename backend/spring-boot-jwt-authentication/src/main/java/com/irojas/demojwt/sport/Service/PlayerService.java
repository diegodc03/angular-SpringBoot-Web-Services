package com.irojas.demojwt.sport.Service;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.Repository.UserRepository;
import com.irojas.demojwt.sport.Model.League;
import com.irojas.demojwt.sport.Model.Player;
import com.irojas.demojwt.sport.Model.PlayerLeague;
import com.irojas.demojwt.sport.Model.TemporaryLeague;
import com.irojas.demojwt.sport.ModelDTO.PlayerDTO;
import com.irojas.demojwt.sport.exception.ExceptionClass.LeagueNotFoundException;
import com.irojas.demojwt.sport.exception.ExceptionClass.PlayerAlreadyInLeagueException;
import com.irojas.demojwt.sport.exception.ExceptionClass.PlayerNotFoundException;
import com.irojas.demojwt.sport.exception.ExceptionClass.UserNotFoundException;
import com.irojas.demojwt.sportRepository.LeagueRepository;
import com.irojas.demojwt.sportRepository.PlayerLeagueRepository;
import com.irojas.demojwt.sportRepository.PlayerRepository;
import com.irojas.demojwt.sportRepository.TemporaryLeagueRepository;

@Service
public class PlayerService {

	private UserRepository userRepository;
	private PlayerRepository playerRepository;
	private LeagueRepository leagueRepository;
	private PlayerLeagueRepository playerLeagueRepository;
	private TemporaryLeagueRepository temporaryLeagueRepository;

	public PlayerService(UserRepository userRepository, 
			PlayerRepository playerRepository,
			LeagueRepository leagueRepository,
			PlayerLeagueRepository playerLeagueRepository,
			TemporaryLeagueRepository temporaryLeagueRepository) {
		super();
		this.userRepository = userRepository;
		this.playerRepository = playerRepository;
		this.leagueRepository = leagueRepository;
		this.playerLeagueRepository = playerLeagueRepository;
		this.temporaryLeagueRepository = temporaryLeagueRepository;
	}


	public void deletePlayerOfLeague(Long leagueId, String email) {
		
		User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new UserNotFoundException("User not found"));
	    
	    Player player = playerRepository.findByUserId(user.getId())
	            .orElseThrow(() -> new PlayerNotFoundException("Player not found"));
	    
	    League league = leagueRepository.findById(leagueId)
	            .orElseThrow(() -> new LeagueNotFoundException("League not found"));
		
	 // Busca la relación PlayerLeague
	    Optional<PlayerLeague> playerLeagueOptional = playerLeagueRepository.findByPlayerAndLeague(player, league);
	    
	    if (playerLeagueOptional.isPresent()) {
	        // Si existe, obtén la relación PlayerLeague
	        //PlayerLeague playerLeague = playerLeagueOptional.get();
	        
	        // Elimina la relación PlayerLeague de la base de datos
	        //playerLeagueRepository.delete(playerLeague);
	        
	    	// Si existe, obtén la relación PlayerLeague
	        PlayerLeague playerLeague = playerLeagueOptional.get();
	        
	        // Elimina la relación de la lista del jugador
	        player.getPlayerLeagues().remove(playerLeague);
	        
	        playerRepository.save(player);
	        // Si está configurado correctamente, esto debería eliminar la relación en cascada
	        // No es necesario guardar el jugador, a menos que hayas cambiado el estado de otra propiedad.
	        
	     
	    } else {
	        throw new RuntimeException("Player is not part of this league");
	    }
	}


	
	
	// Tengo que poner un tipo de error para que devuelva ese tipo y no uno tipico
	public void joinLeague(String email, Long leagueId) {
	    User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new UserNotFoundException("User not found"));
	    
	    Player player = playerRepository.findByUserId(user.getId())
	            .orElseThrow(() -> new PlayerNotFoundException("Player not found"));
	    
	    League league = leagueRepository.findById(leagueId)
	            .orElseThrow(() -> new LeagueNotFoundException("League not found"));
	    
	    Optional<PlayerLeague> existingPlayerLeague = playerLeagueRepository.findByPlayerAndLeague(player, league);
	    
	    if (existingPlayerLeague.isPresent()) {
	        throw new PlayerAlreadyInLeagueException("Player is already part of this league");
	    }
	    
	    PlayerLeague playerLeague = new PlayerLeague(player, league);
	    player.getPlayerLeagues().add(playerLeague);
	    
	    playerRepository.save(player);
	   
	}
	
	
	public void saveTemporaryUserRequest(String username, Long leagueId) {
		// TODO Auto-generated method stub
		String token = UUID.randomUUID().toString();
		TemporaryLeague leagueT = new TemporaryLeague(username, leagueId, token);
		
		temporaryLeagueRepository.save(leagueT);
		
		String message = "Quiere aceptar al usuario"+username+"pulse la url para aceptar \n"
				+ " ------>> 	http://localhost:8080/sport/player/accept-to-join-league?token="+token;
		
		sendEmail("diegodecm03@gmail.com", "Añadir Usuario a la liga", message);
	}
	
	
	public void acceptRequestToJoinLeague(String token) {
		// TODO Auto-generated method stub
		
		
		TemporaryLeague tempReqUserLeague = temporaryLeagueRepository.findByToken(token)
				.orElseThrow(() -> new RuntimeException("not find the token") );
		
		this.joinLeague(tempReqUserLeague.getEmail(), tempReqUserLeague.getLeagueId());
		
	}
	
	
	
	
	public int sendEmail(String mailReceiver, String subject, String messageToSend) {
	    
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
	
	
	
	


	public List<PlayerDTO> getAllPlayers(Long leagueId) {
		// TODO Auto-generated method stub
		League league = leagueRepository.findById(leagueId)
				.orElseThrow(() -> new LeagueNotFoundException("League not found"));
		
		List<PlayerLeague> playerLeagues = playerLeagueRepository.findByLeague(league);
		
		// Extraer los jugadores de la lista de PlayerLeague
	    /*List<Player> players = playerLeagues.stream()
	            .map(PlayerLeague::getPlayer) // Mapear a jugadores
	            .collect(Collectors.toList()); // Recoger en una lista*/
		
		// Extraer los jugadores y convertir a PlayerDTO
	    List<PlayerDTO> playerDTOs = playerLeagues.stream()
	            .map(playerLeague -> {
	                Player player = playerLeague.getPlayer(); // Obtener el jugador
	                return new PlayerDTO(player.getId(), player.getUser().getFirstname()); // Asumiendo que quieres el nombre del usuario
	            })
	            .collect(Collectors.toList()); // Recoger en una lista

	    return playerDTOs; // Devolver la lista de DTOs
	}
	
	
}
