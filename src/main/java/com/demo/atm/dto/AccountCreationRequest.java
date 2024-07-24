package com.demo.atm.dto;


public class AccountCreationRequest {
	
	    private String pin; // PIN for the account
	    private double initialBalance;
	    
	    private String accountHolderName;
	    
	    
	    
		public String getPin() {
			return pin;
		}
		public void setPin(String pin) {
			this.pin = pin;
		}
		public double getInitialBalance() {
			return initialBalance;
		}
		public void setInitialBalance(double initialBalance) {
			this.initialBalance = initialBalance;
			
		}
		
		 public String getAccountHolderName() {
				return accountHolderName;
			}
			public void setAccountHolderName(String accountHolderName) {
				this.accountHolderName = accountHolderName;
			}
	    

}
