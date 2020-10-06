package com.atm.atm_service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;

import com.atm.atm_service.service.ATMServiceImplTest;

@RunWith(Suite.class)
@SuiteClasses({ ATMServiceImplTest.class })
@SpringBootTest
class AtmServiceApplicationTests {

}
