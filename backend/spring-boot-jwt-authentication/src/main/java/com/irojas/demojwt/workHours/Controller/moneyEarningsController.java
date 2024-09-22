package com.irojas.demojwt.workHours.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.irojas.demojwt.workHours.Model.Money;
import com.irojas.demojwt.workHours.Model.WorkingRoles;
import com.irojas.demojwt.workHours.ModelDTO.EarningsDTO;
import com.irojas.demojwt.workHours.ModelDTO.PaidMoneyRequestDTO;
import com.irojas.demojwt.workHours.ModelDTO.WorkingRoleDTO;
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
	
	@GetMapping("/get-arnings-by-seasonId/{seasonId}")
	public ResponseEntity<EarningsDTO> getEarningsBySeasonId(
			@PathVariable Long seasonId,
			@AuthenticationPrincipal UserDetails currentUser){
		
		try {
			EarningsDTO earnings = this.moneyEarningsService.getEarnings(seasonId, currentUser.getUsername());
			return ResponseEntity.ok(earnings);
			
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	
	
}
