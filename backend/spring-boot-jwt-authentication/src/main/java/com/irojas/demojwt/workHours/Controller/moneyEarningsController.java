package com.irojas.demojwt.workHours.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.irojas.demojwt.workHours.Model.Money;
import com.irojas.demojwt.workHours.ModelDTO.PaidMoneyRequestDTO;
import com.irojas.demojwt.workHours.Service.MoneyEarningsService;

@Controller
@RequestMapping("/moneyEarnings")
public class moneyEarningsController {

	
	private MoneyEarningsService moneyEarningsService;
	
	public moneyEarningsController(MoneyEarningsService moneyEarningsService) {
		super();
		this.moneyEarningsService = moneyEarningsService;
	}




	@PostMapping("/add-paid-money")
	public ResponseEntity<String> addPaidMoney(
			@RequestBody PaidMoneyRequestDTO paidMoneyRequest,
			@AuthenticationPrincipal UserDetails currentUser
			){
		
		try {
			Money money = this.moneyEarningsService.addPaidMoney(paidMoneyRequest, currentUser.getUsername());
			return ResponseEntity.ok(money.toString());
		}catch(Exception e) {
			return ResponseEntity.badRequest().body("Error adding match: " + e.getMessage());
		}
		
		
		
	}
	
	
	
}
