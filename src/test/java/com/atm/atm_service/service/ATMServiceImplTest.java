package com.atm.atm_service.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.atm.atm_service.AtmServiceApplication;
import com.atm.atm_service.dto.AccountCreationRequestDTO;

/**
 * 
 * @author puneet
 *
 */
@RunWith(SpringRunner.class)
@EnableConfigurationProperties
@SpringBootTest(classes = { AtmServiceApplication.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ATMServiceImplTest {
	private AccountCreationRequestDTO dto1, dto2;
	private Long accountNumber1, accountNumber2;
	private Double amount1, amount2, amount3;

	@Before
	public void init() {
		dto1 = null;
		dto2 = AccountCreationRequestDTO.builder().accountHolderName("Puneet").balance(200).pin(1234).build();
		accountNumber1 = 999L;
		accountNumber2 = 1000L;
		amount1 = -1D;
		amount2 = 500D;
		amount3 = 10D;
	}

	@Autowired
	private ATMService atmService;

	@Test
	public void invalidAccountCreattionTest1() {
		assertThrows(RuntimeException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				atmService.createAccount(dto1);
			}
		});
	}

	@Test
	public void validAccountCreattionTest2() {
		String response = atmService.createAccount(dto2);
		assertTrue(!response.isEmpty());
	}

	@Test
	public void invalidcheckBalanceTest3() {
		assertThrows(RuntimeException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				atmService.checkBalance(accountNumber1);
			}
		});
	}

	@Test
	public void validcheckBalanceTest4() {
		Double response = atmService.checkBalance(accountNumber2);
		assertNotNull(response);
	}

	@Test
	public void invalidWithdrawForInvalidAccounTest5() {
		assertThrows(RuntimeException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				atmService.withdraw(accountNumber1, amount2);
			}
		});
	}

	@Test
	public void invalidWithdrawOfAmountTest6() {
		assertThrows(RuntimeException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				atmService.withdraw(accountNumber2, amount2);
			}
		});
	}

	@Test
	public void validWithdrawTest7() {
		String response = atmService.withdraw(accountNumber2, amount3);
		assertTrue(!response.isEmpty());
	}

	@Test
	public void invalidDepositForInvalidAccounTest8() {
		assertThrows(RuntimeException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				atmService.deposit(accountNumber1, amount2);
			}
		});
	}

	@Test
	public void invalidDepositOfAmountTest9() {
		assertThrows(RuntimeException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				atmService.deposit(accountNumber2, amount1);
			}
		});
	}

	@Test
	public void validDepositTest10() {
		String response = atmService.deposit(accountNumber2, amount3);
		assertTrue(!response.isEmpty());
	}
}
