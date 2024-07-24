package com.demo.atm.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;

//ATMUser.java


@Entity
public class ATMUser {

 @Id
 private String accountNumber;
 private String pin;
 private double balance;
 private String accountHolderName;
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
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
	
}
public String getAccountHolderName() {
	return accountHolderName;
}
public void setAccountHolderName(String accountHolderName) {
	this.accountHolderName = accountHolderName;
}


 // Constructors, getters, setters
}

