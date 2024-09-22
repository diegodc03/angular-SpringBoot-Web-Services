package com.irojas.demojwt.workHours.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.Repository.UserRepository;
import com.irojas.demojwt.workHours.Model.Match;
import com.irojas.demojwt.workHours.Model.Money;
import com.irojas.demojwt.workHours.Model.UserMatch;
import com.irojas.demojwt.workHours.Model.WorkingRoles;
import com.irojas.demojwt.workHours.ModelDTO.RoleMatchPaymentRequest;
import com.irojas.demojwt.workHours.Repository.MatchRepository;
import com.irojas.demojwt.workHours.Repository.MoneyRepository;
import com.irojas.demojwt.workHours.Repository.UserMatchRepository;

@Service
public class UserMatchService {
	
	private UserMatchRepository userMatchRepository;
	
	private MatchRepository matchRepository;
	
	private UserRepository userRepository;
	
	private MoneyRepository moneyRepository;
	
	
	
	
	public UserMatchService(UserMatchRepository userMatchRepository, MatchRepository matchRepository,
			UserRepository userRepository, MoneyRepository moneyRepository) {
		super();
		this.userMatchRepository = userMatchRepository;
		this.matchRepository = matchRepository;
		this.userRepository = userRepository;
		this.moneyRepository = moneyRepository;
	}


	// Solo el usuario puede añadir o actualizar su trabajo y pago en partidos pasados o en la misma fecha
    public void addOrUpdateWorkAndPayment(List<RoleMatchPaymentRequest> roleRequestList, String email) {
        
    	
    	for(RoleMatchPaymentRequest roleRequest : roleRequestList) {
    		
    		Match match = matchRepository.findById(roleRequest.getMatchId())
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
            WorkingRoles role = WorkingRoles.valueOf(roleRequest.getRole().toUpperCase());
            userMatch.setWorkingRol(role);
    		
            
            //añadir al total 
            Optional<Money> optMoney = moneyRepository.findBySeasonAndUser(match.getSeason(), user);
            
            if(!optMoney.isPresent()) {
            	Money money = new Money(user, match.getSeason(), role.getSalary(), 0.0, role.getSalary());
                
                moneyRepository.save(money);
            }else {
            	Money money = optMoney.get();
            	money.setMoneyToPay(money.getMoneyToPay() + role.getSalary());
            	money.setTotalMoneyPaid(money.getTotalMoneyPaid() + role.getSalary());
            	moneyRepository.save(money);
            }
            
            userMatchRepository.save(userMatch);
            
    	}
    	
    	
    	
    	
    	
        
        
        
        
        
        
    }


	public void deleteMatchWork(Long matchId, String username) {
		// TODO Auto-generated method stub
		Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found"));
		
		User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
		
		UserMatch userMatch = userMatchRepository.findByUserAndMatch(user, match)
				.orElseThrow(() -> new RuntimeException("Match not found"));
		
		Money money = moneyRepository.findBySeasonAndUser(match.getSeason(), user)
				.orElseThrow(() -> new RuntimeException("earnings not found"));
		
		money.setTotalMoneyPaid(money.getTotalMoneyPaid() - userMatch.getWorkingRol().getSalary());
		
		// if true, restamos part of moneypaid
		if(userMatch.isPaid()) {
			money.setMoneyPaid(money.getMoneyPaid() - userMatch.getWorkingRol().getSalary());
		}else {
			money.setMoneyToPay(money.getMoneyToPay() - userMatch.getWorkingRol().getSalary());
		}
		
		
		moneyRepository.save(money);
		userMatchRepository.delete(userMatch);
	}
	
	
	
	
	
	
	
}
