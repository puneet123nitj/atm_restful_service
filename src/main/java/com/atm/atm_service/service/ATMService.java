package com.atm.atm_service.service;

import com.atm.atm_service.dto.AccountCreationRequestDTO;

/**
 * 
 * @author puneet
 *
 */
public interface ATMService {
	
	String createAccount(AccountCreationRequestDTO accountCreationRequestDTO);
	
	Double checkBalance(Long accountNumber);

	String withdraw(Long accountNumber, Double amount);

	String deposit(Long accountNumber, Double amount);
}
