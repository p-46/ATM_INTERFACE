package com.demo.atm.dto;

public class VerifyPinRequest {
	
	private String accountNumber;
	private String pin;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	} 		
	
	
	

}
