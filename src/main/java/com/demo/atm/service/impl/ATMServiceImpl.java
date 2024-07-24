package com.demo.atm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.atm.dto.ChangePinRequest;
import com.demo.atm.dto.MiniStatementResponse;
import com.demo.atm.dto.WithdrawalRequest;
import com.demo.atm.dto.WithdrawalResponse;
import com.demo.atm.entity.ATMUser;
import com.demo.atm.entity.Transaction;
import com.demo.atm.repo.ATMUserRepository;
import com.demo.atm.repo.TransactionRepository;
import com.demo.atm.service.ATMService;


@Service
public class ATMServiceImpl implements ATMService {
	
    private static final int ACCOUNT_NUMBER_LENGTH = 8;

	
	@Autowired
    private ATMUserRepository userRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
	
	
	
//	@Autowired
    public ResponseEntity<?> verifyPin(String accountNumber, String pin) {
        Optional<ATMUser>user = userRepository.findById(accountNumber);
        
        if(user.isPresent()) {
        	ATMUser userFound=user.get();
        	String oldPin=userFound.getPin();
        
        if(oldPin.equals(pin)) {
            
            return new ResponseEntity<>("Welcome , Correct Pin", HttpStatus.OK);
        }
        else {
        	
            return new ResponseEntity<>("Incorrect , Try Again", HttpStatus.CONFLICT);
        }
        
        }
        else {
        	return new ResponseEntity<>("User Not Found" , HttpStatus.NOT_FOUND);
        }
    }
//	@Autowired
    public String changePin(ChangePinRequest request) {
        ATMUser user = userRepository.findById(request.getAccountNumber()).orElse(null);
        if(user==null) {
        	return "User Not Found"; 
        }
        else if(!user.getPin().equals(request.getCurrentPin())) {
            
            return "Old Pin Incorrect";
        }
        else {
        	user.setPin(request.getNewPin());
            userRepository.save(user);
            return "Pin Changed";
        }
        
    }

//	@Autowired
    public ResponseEntity<MiniStatementResponse>  getMiniStatement(String accountNumber) {
		MiniStatementResponse miniStatementResponse= new MiniStatementResponse();
		miniStatementResponse.setAccountNumber(accountNumber);
		
		List<Transaction> transactions = new ArrayList<Transaction>();
        transactions =transactionRepository.getTransactionsByAccountNumber(accountNumber);
        miniStatementResponse.setTransactions(transactions);
		return ResponseEntity.ok(miniStatementResponse);
    }

    
    public WithdrawalResponse withdrawAmount(WithdrawalRequest request) throws RuntimeException{
    	

    	
        ATMUser user = userRepository.findById(request.getAccountNumber()).orElseThrow(() -> new RuntimeException("Account does Not Exist "));
        WithdrawalResponse withdrawalResponse= new WithdrawalResponse();
    	Transaction transaction = new Transaction();
    	transaction.setAccountNumber(request.getAccountNumber());
        Double total = user.getBalance()-request.getAmount();

    	transaction.setAmount(request.getAmount());
    	transaction.setDescription("Withdraw");
        transaction.setTransactionDate(new Date());
        transaction.setBalance(total);
        transactionRepository.save(transaction);
        
        withdrawalResponse.setAccountNumber(request.getAccountNumber());
        withdrawalResponse.setBalance(total);
        withdrawalResponse.setStatus("Success");
        
        user.setBalance(total);
        userRepository.save(user);
        return withdrawalResponse;
        
        

    }
    
    public ResponseEntity<ATMUser>   createNewAccount(ATMUser user) {
        // Generate account number (replace with your logic)
    	try {
    		user.setAccountNumber(generateAccountNumber());
//    		user.setPin(passwordEncoder.encode(user.getPin()));
            userRepository.save(user);
            return new ResponseEntity<>(user , HttpStatus.CREATED);
    	}
    	catch(Exception e){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
        
    }
    
    public String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(ACCOUNT_NUMBER_LENGTH);
        
        // Ensure the generated account number is exactly 8 digits
        while (sb.length() < ACCOUNT_NUMBER_LENGTH) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

}
