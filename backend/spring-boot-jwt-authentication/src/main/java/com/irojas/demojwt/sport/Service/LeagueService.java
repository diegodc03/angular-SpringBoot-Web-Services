package com.irojas.demojwt.sport.Service;



import java.util.Comparator;
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

import org.springframework.stereotype.Service;

import com.irojas.demojwt.Auth.Model.TemporaryUser;
import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.Repository.UserRepository;
import com.irojas.demojwt.sport.Model.League;
import com.irojas.demojwt.sport.Model.LeagueType;
import com.irojas.demojwt.sport.Model.Player;
import com.irojas.demojwt.sport.Model.PlayerLeague;
import com.irojas.demojwt.sport.Model.TemporaryLeague;
import com.irojas.demojwt.sport.ModelDTO.LeagueDTO;
import com.irojas.demojwt.sport.ModelDTO.PlayMatchDTOWithPlayers;
import com.irojas.demojwt.sport.ModelDTO.PlayerDTO;
import com.irojas.demojwt.sport.ModelDTO.PlayerLeagueDTO;
import com.irojas.demojwt.sport.ModelDTO.LeagueInformationDTO;
import com.irojas.demojwt.sport.ModelDTO.SetDTO;
import com.irojas.demojwt.sportRepository.LeagueRepository;
import com.irojas.demojwt.sportRepository.PlayerLeagueRepository;
import com.irojas.demojwt.sportRepository.PlayerRepository;
import com.irojas.demojwt.sportRepository.TemporaryLeagueRepository;


@Service
public class LeagueService {
	
	private LeagueRepository leagueRepository;
	private PlayerRepository playerRepository;
	private UserRepository userRepository;
	private PlayerLeagueRepository playerLeagueRepository;
	private TemporaryLeagueRepository temporaryLeagueRepository;
	
	private LeagueService(LeagueRepository leagueRepository, 
						PlayerRepository playerRepository, 
						UserRepository userRepository, 
						PlayerLeagueRepository playerLeagueRepository,
						TemporaryLeagueRepository temporaryLeagueRepository) {
		this.leagueRepository = leagueRepository;
		this.playerRepository = playerRepository;
		this.userRepository = userRepository;
		this.playerLeagueRepository = playerLeagueRepository;
		this.temporaryLeagueRepository = temporaryLeagueRepository;
	}
	
	

    public void createLeague(LeagueDTO leagueDTO) {
        try {
            League league = new League();
            league.setName(leagueDTO.getName());
            league.setLeagueType(LeagueType.valueOf(leagueDTO.getLeagueType()));
            league.setRequireRequest(leagueDTO.isRequireRequest());            
            
            leagueRepository.save(league);
      
        } catch (Exception e) {
        	throw new RuntimeException("Failed to create the league ", e);
        }
    }

    public void deleteLeague(Long leagueId) {
        Optional<League> optionalLeague = leagueRepository.findById(leagueId);

        if (optionalLeague.isPresent()) {
        	try {
        		leagueRepository.delete(optionalLeague.get());
        	}catch(Exception e) {
        		throw new RuntimeException("Failed to delete the league ", e);
        	}
        } 
    }
	
    
	public String getOutOfPlayerOfLeague(Long playerId) {
		
		
		
		return null;
		
	}



	public List<LeagueDTO> getAllLeagues( String string) {
		// TODO Auto-generated method stub
		try {
			List<League> leagues = leagueRepository.findAll();
			
			if(leagues != null) {
				return leagues.stream()
						.map(l -> 
						new LeagueDTO(l.getId(), l.getName(), l.getLeagueType().toString(), l.isRequireRequest())).collect(Collectors.toList());
						
			}
			else {
				throw new RuntimeException("Failed to get all leagues the league ");
			}
		}catch(Exception e){
			throw new RuntimeException("Failed to get all leagues the league ", e);
		}
	}



	public List<LeagueDTO> getLeaguesJoined(String email) {
		// TODO Auto-generated method stub
		try {
			User user = userRepository.findByEmail(email)
					.orElseThrow(() -> new RuntimeException("Player not found"));
			
	        // Busca el jugador a partir del username.
	        Player player = playerRepository.findByUserId(user.getId())
	            .orElseThrow(() -> new RuntimeException("Player not found"));

	        // Busca las relaciones de PlayerLeague para este jugador.
	        List<PlayerLeague> playerLeagues = playerLeagueRepository.findByPlayerId(player.getId());

	        // Convierte cada PlayerLeague en un LeagueDTO
	        return playerLeagues.stream()
	            .map(playerLeague -> {
	                League league = playerLeague.getLeague();
	                return new LeagueDTO(
	                    league.getId(),
	                    league.getName(),
	                    league.getLeagueType().toString(),
	                    league.isRequireRequest()
	                );
	            })
	            .collect(Collectors.toList());

	    } catch (Exception e) {
	        throw new RuntimeException("Failed to get leagues joined by the user", e);
	    }
	}



	public LeagueInformationDTO getLeagueInfo(Long leagueId) {
		// TODO Auto-generated method stub
		try {
			 League league = this.leagueRepository.findById(leagueId)
					 .orElseThrow(() -> new RuntimeException("league not found"));
			
			 // Convertir lista de partidos a MatchDTO
			 List<PlayMatchDTOWithPlayers> matches = league.getMatchPlayed().stream()
					    .map(play -> new PlayMatchDTOWithPlayers(
					        play.getId(),
					        play.getFecha(),
					        play.getUbicacion(),
					        play.getLeague().getId(),
					        play.getGanadorEquipo().toString(),
					        play.getSets().stream()
					            .map(set -> new SetDTO(
					                set.getId(),
					                set.getNumeroSet(),
					                set.getJuegosEquipo1(),
					                set.getJuegosEquipo2()
					            )).collect(Collectors.toList()),
					        new PlayerDTO(play.getJugador1().getId(), play.getJugador1().getUser().getFirstname()),
					        play.getJugador2() != null ? new PlayerDTO(play.getJugador2().getId(), play.getJugador2().getUser().getFirstname()) : null,
					        new PlayerDTO(play.getJugador3().getId(), play.getJugador3().getUser().getFirstname()),
					        play.getJugador4() != null ? new PlayerDTO(play.getJugador4().getId(), play.getJugador4().getUser().getFirstname()) : null
					    ))
					    .collect(Collectors.toList());

		        // Convertir lista de PlayerLeague a PlayerLeaguesdtoModule
		        List<PlayerLeagueDTO> playerLeagues = league.getPlayerLeagues().stream()
		                .map(playerLeague -> new PlayerLeagueDTO(
		                        playerLeague.getId(),
		                        playerLeague.getPlayer().getId(),
		                        playerLeague.getPlayer().getUser().getFirstname(),
		                        playerLeague.getLeague().getId(),
		                        playerLeague.getPartidosGanados(),
		                        playerLeague.getPartidosPerdidos(),
		                        playerLeague.getPartidosJugados(),
		                        playerLeague.getJuegosGanados(),
		                        playerLeague.getJuegosPerdidos(),
		                        playerLeague.getJuegosTotales(),
		                        playerLeague.getPuntosClasificacion()
		                ))
		                .sorted(Comparator.comparingInt(PlayerLeagueDTO::getPuntosClasificacion).reversed()
		                        .thenComparing(Comparator.comparingInt(PlayerLeagueDTO::getPartidosGanados).reversed()
		                        .thenComparing(Comparator.comparingInt(PlayerLeagueDTO::getPartidosJugados).reversed()
		                        .thenComparing(Comparator.comparingInt(PlayerLeagueDTO::getJuegosGanados).reversed()))))
		                .collect(Collectors.toList());

		        // Crear el LeagueInformationDTO con toda la información
		        return new LeagueInformationDTO(
		                league.getId(),
		                league.getName(),
		                league.getLeagueType().toString(),
		                league.isRequireRequest(),
		                matches,
		                playerLeagues
		        );
			
			
		}catch(Exception e){
			throw new RuntimeException("Failed to getLeagueInfo the league ", e);
		}
	
	}



	public void saveTemporaryUserRequest(String username, Long leagueId) {
		// TODO Auto-generated method stub
		temporaryLeagueRepository.save(new TemporaryLeague(username, leagueId));
		
		String message = "Quiere aceptar al usuario"+username;
		
		sendRegisterEmail("diegodecm03@gmail.com", "Añadir Usuario a la liga", message);
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
}
