package com.irojas.demojwt.workHours.Service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.Repository.UserRepository;
import com.irojas.demojwt.workHours.Model.Match;
import com.irojas.demojwt.workHours.Model.UserMatch;
import com.irojas.demojwt.workHours.Model.WorkingRoles;
import com.irojas.demojwt.workHours.Repository.MatchRepository;
import com.irojas.demojwt.workHours.Repository.UserMatchRepository;

@Service
public class UserMatchService {
	
	private UserMatchRepository userMatchRepository;
	
	private MatchRepository matchRepository;
	
	private UserRepository userRepository;
	
	
	
	
	
	public UserMatchService(UserMatchRepository userMatchRepository, MatchRepository matchRepository,
			UserRepository userRepository) {
		super();
		this.userMatchRepository = userMatchRepository;
		this.matchRepository = matchRepository;
		this.userRepository = userRepository;
	}


	// Solo el usuario puede añadir o actualizar su trabajo y pago en partidos pasados o en la misma fecha
    public UserMatch addOrUpdateWorkAndPayment(Long matchId, String email, WorkingRoles role) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found"));

        // Verificar que el partido sea pasado o en la misma fecha actual
        if (match.getMatchDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("Cannot add work or payment for future matches.");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserMatch userMatch = userMatchRepository.findByUserAndMatch(user, match)
                .orElse(new UserMatch(user, match));  // Si no existe la relación, la creamos.

        // Añadir los datos de trabajo y el pago
        userMatch.setWorkingRol(role);

        return userMatchRepository.save(userMatch);
    }


	public void deleteMatchWork(Long matchId, String username) {
		// TODO Auto-generated method stub
		Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found"));
		
		User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
		
		UserMatch userMatch = userMatchRepository.findByUserAndMatch(user, match)
				.orElseThrow(() -> new RuntimeException("Match not found"));
		
		
		userMatchRepository.delete(userMatch);
	}
	
	
	
	
	
	
	
}
