package com.atm.atm_service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
@SequenceGenerator(name="seq", initialValue=1000)
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator ="seq")
	@Column(insertable = false, name = "accountNumber", nullable = false)
	private Long accountNumber;
	@Column(name = "accountHolderName")
	private String accountHolderName;
	@Column(name = "pin")
	private Integer pin;
	@Column(name = "balance")
	private Double balance;
}
