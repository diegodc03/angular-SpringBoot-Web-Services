package com.irojas.demojwt.workHours.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.irojas.demojwt.Auth.Model.User;
import com.irojas.demojwt.Auth.Repository.UserRepository;
import com.irojas.demojwt.workHours.Model.Money;
import com.irojas.demojwt.workHours.Model.Season;
import com.irojas.demojwt.workHours.ModelDTO.EarningsDTO;
import com.irojas.demojwt.workHours.ModelDTO.PaidMoneyRequestDTO;
import com.irojas.demojwt.workHours.ModelDTO.WorkingRoleDTO;
import com.irojas.demojwt.workHours.Repository.MoneyRepository;
import com.irojas.demojwt.workHours.Repository.SeasonRepository;


@Service
public class MoneyEarningsService {

	
	private UserRepository userRepository;
	private SeasonRepository seasonRepository;
	private MoneyRepository moneyRepository;
	

	public MoneyEarningsService(UserRepository userRepository, SeasonRepository seasonRepository,
			MoneyRepository moneyRepository) {
		super();
		this.userRepository = userRepository;
		this.seasonRepository = seasonRepository;
		this.moneyRepository = moneyRepository;
	}


	public Money addPaidMoney(PaidMoneyRequestDTO paidMoneyRequestDTO, String email) {
		
		User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
		
		Season season = seasonRepository.findById(paidMoneyRequestDTO.getSeasonId())
				.orElseThrow(() -> new RuntimeException("Season not found"));
		
		Money money = moneyRepository.findBySeasonAndUser(season, user)
			.orElseThrow(() -> new RuntimeException("Earnings not found, yo can add a paid if yo dont have any match wotked"));
		
		money.paid(paidMoneyRequestDTO.getMoneyPaid());
		
		return moneyRepository.save(money);
	}


	public EarningsDTO getEarnings(Long seasonId, String email) {
		// TODO Auto-generated method stub
		if (seasonId == null || email == null || email.isEmpty()) {
	        throw new IllegalArgumentException("Season ID and email must be provided");
	    }
		
		User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
		
		Season season = seasonRepository.findById(seasonId)
				.orElseThrow(() -> new RuntimeException("Season not found"));
		
		Money money = moneyRepository.findBySeasonAndUser(season, user)
				.orElseThrow(() -> new RuntimeException("Earnings not found, yo can add a paid if yo dont have any match wotked"));
		
		
		return new EarningsDTO(money);
		
	}


	
	
	
}
