package com.atm.atm_service.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atm.atm_service.dto.AccountCreationRequestDTO;
import com.atm.atm_service.model.Account;
import com.atm.atm_service.repository.AccountRepository;
import com.atm.atm_service.service.ATMService;

/**
 * 
 * @author puneet
 *
 */
@Service
public class ATMServiceImpl implements ATMService {
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public String createAccount(AccountCreationRequestDTO accountCreationRequestDTO) {
		Account account = new Account();
		BeanUtils.copyProperties(accountCreationRequestDTO, account);
		Account result = accountRepository.save(account);
		if (result != null) {
			return "Account Created Successful,Your Account Number is = " + result.getAccountNumber();
		}
		throw new RuntimeException("Account creation fail due to some internal error");

	}

	@Override
	public Double checkBalance(Long accountNumber) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		if (account != null)
			return account.getBalance();
		throw new RuntimeException("Account doesn't exist,please create first.");
	}

	@Transactional
	@Override
	public String withdraw(Long accountNumber, Double amount) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		if (account != null && Double.compare(account.getBalance(), amount) >= 0) {
			account.setBalance(account.getBalance() - amount);
			Account result = accountRepository.save(account);
			if (result != null) {
				return "Amount Withdrawal Successful";
			} else {
				return "Amount Withdrawal Fail";
			}
		} else if (account == null) {
			throw new RuntimeException("Account doesn't exist,please create first.");
		} else {
			throw new RuntimeException("There is not enough balance in your account.");
		}
	}

	@Transactional
	@Override
	public String deposit(Long accountNumber, Double amount) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		if (account != null && Double.compare(amount, 0.0) > 0) {
			account.setBalance(account.getBalance() + amount);
			Account result = accountRepository.save(account);
			if (result != null) {
				return "Amount Deposit Successful";
			} else {
				return "Amount Deposit Fail";
			}
		} else if (account == null) {
			throw new RuntimeException("Account doesn't exist,please create first.");
		} else {
			throw new RuntimeException("deposit amount is not valid.");
		}
	}

}
