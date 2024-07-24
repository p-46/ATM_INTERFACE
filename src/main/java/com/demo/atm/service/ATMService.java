package com.demo.atm.service;

import org.springframework.http.ResponseEntity;

import com.demo.atm.dto.ChangePinRequest;
import com.demo.atm.dto.MiniStatementResponse;
import com.demo.atm.dto.WithdrawalRequest;
import com.demo.atm.dto.WithdrawalResponse;
import com.demo.atm.entity.ATMUser;

public interface ATMService {
	 ResponseEntity<?> verifyPin(String accountNumber, String pin);
	    String changePin(ChangePinRequest request);
	    ResponseEntity<MiniStatementResponse> getMiniStatement(String accountNumber);
	    WithdrawalResponse withdrawAmount(WithdrawalRequest request);
		ResponseEntity<ATMUser> createNewAccount(ATMUser newUser);
	

}
