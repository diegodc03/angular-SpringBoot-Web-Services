package com.irojas.demojwt.workHours.Service;

import java.time.LocalDate;
import java.util.Optional;

import com.irojas.demojwt.Model.User;
import com.irojas.demojwt.Repsository.UserRepository;
import com.irojas.demojwt.workHours.Model.Match;
import com.irojas.demojwt.workHours.Model.UserMatch;
import com.irojas.demojwt.workHours.Model.WorkingRoles;
import com.irojas.demojwt.workHours.Repository.MatchRepository;
import com.irojas.demojwt.workHours.Repository.UserMatchRepository;

public class UserMatchService {
	
	private UserMatchRepository userMatchRepository;
	
	private MatchRepository matchRepository;
	
	private UserRepository userRepository;
	
	
	// Solo el usuario puede añadir o actualizar su trabajo y pago en partidos pasados o en la misma fecha
    public void addOrUpdateWorkAndPayment(Long matchId, String username, WorkingRoles role) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found"));

        // Verificar que el partido sea pasado o en la misma fecha actual
        if (match.getMatchDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("Cannot add work or payment for future matches.");
        }

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserMatch userMatch = userMatchRepository.findByUserAndMatch(user, match)
                .orElse(new UserMatch(user, match));  // Si no existe la relación, la creamos.

        // Añadir los datos de trabajo y el pago
        userMatch.setWorkingRol(role);

        userMatchRepository.save(userMatch);
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
