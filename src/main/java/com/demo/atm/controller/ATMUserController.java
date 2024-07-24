package com.demo.atm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.atm.dto.AccountCreationRequest;
import com.demo.atm.dto.ChangePinRequest;
//import com.demo.atm.dto.MiniStatementResponse;
import com.demo.atm.dto.VerifyPinRequest;
import com.demo.atm.dto.WithdrawalRequest;
import com.demo.atm.dto.WithdrawalResponse;
import com.demo.atm.entity.ATMUser;
import com.demo.atm.repo.ATMUserRepository;
import com.demo.atm.service.ATMService;
//import com.demo.atm.util.AccountNumberGenerator;

@RestController
@RequestMapping("/api/atm")
public class ATMUserController {

    @Autowired
    private ATMService atmService;
    
    
    
    @Autowired
    ATMUserRepository userRepository;
    
    @PostMapping("/createAccount")
    public ResponseEntity<ATMUser> createAccount(@RequestBody AccountCreationRequest request) {
        ATMUser newUser = new ATMUser();
        newUser.setPin(request.getPin());
        newUser.setBalance(request.getInitialBalance());
        newUser.setAccountHolderName(request.getAccountHolderName());

        ResponseEntity<ATMUser> createdUser = atmService.createNewAccount(newUser);
        return createdUser;
    }

    @PostMapping("/verifyPin")
    public ResponseEntity<?> verifyPin(@RequestBody VerifyPinRequest request) {
    	
         return atmService.verifyPin(request.getAccountNumber(), request.getPin());
         
    }

    @PostMapping("/changePin")
    public String changePin(@RequestBody ChangePinRequest request) {
        return atmService.changePin(request);
    }

    @GetMapping("/miniStatement/{accountNumber}")
    public ResponseEntity<?> getMiniStatement(@PathVariable String accountNumber) {
    	Optional<ATMUser> account = userRepository.findById(accountNumber);
    	if(account.isPresent()) {
    		return atmService.getMiniStatement(accountNumber);
    	}
    	return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
    	
        
    }

    @PostMapping("/withdraw")
    public ResponseEntity<WithdrawalResponse> withdrawAmount(@RequestBody WithdrawalRequest request) {
    	WithdrawalResponse withdrawalResponse= new WithdrawalResponse();
    	
		ATMUser user = userRepository.findById(request.getAccountNumber()).orElse(null);
		if(user==null) {
			withdrawalResponse.setStatus("USER NOT FOUND");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(withdrawalResponse);
		}
		if(user.getBalance()>request.getAmount()) {
	        withdrawalResponse= atmService.withdrawAmount(request);
	        return ResponseEntity.ok(withdrawalResponse);

		}else {
			withdrawalResponse.setBalance(user.getBalance());
			withdrawalResponse.setStatus("Insufficient Funds");
			withdrawalResponse.setAccountNumber(request.getAccountNumber());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(withdrawalResponse);
			
		}
       
    }
}

