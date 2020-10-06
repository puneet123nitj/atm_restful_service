package com.atm.atm_service.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author puneet
 *
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountCreationRequestDTO {
	@NotNull
	private String accountHolderName;
	@NotNull
	private Integer pin;
	private double balance;
}
