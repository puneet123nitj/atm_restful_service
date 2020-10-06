package com.atm.atm_service.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atm.atm_service.dto.AccountCreationRequestDTO;
import com.atm.atm_service.service.ATMService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author puneet
 *
 */
@RestController
@RequestMapping(value = "/atm")
@CrossOrigin
@Slf4j
public class ATMController {
	@Autowired
	private ATMService atmService;
	
	@PostMapping("/create-account")
	public ResponseEntity<Object> createAccount(@Valid AccountCreationRequestDTO accountCreationRequestDTO) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(atmService.createAccount(accountCreationRequestDTO));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

	@RequestMapping("/check-balance")
	public ResponseEntity<Object> checkbalance(@RequestParam Long accountNumber) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(atmService.checkBalance(accountNumber));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}
	}

	@RequestMapping("/withdraw")
	public ResponseEntity<Object> withdraw(@RequestParam Long accountNumber, @RequestParam Double amount) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(atmService.withdraw(accountNumber, amount));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}
	}

	@RequestMapping("/deposit")
	public ResponseEntity<Object> deposit(@RequestParam Long accountNumber, @RequestParam Double amount) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(atmService.deposit(accountNumber, amount));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}
	}

}
