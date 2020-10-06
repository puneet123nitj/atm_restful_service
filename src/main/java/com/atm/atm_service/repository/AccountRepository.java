package com.atm.atm_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atm.atm_service.model.Account;

/**
 * 
 * @author puneet
 *
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByAccountNumber(Long accountNumber);

}
