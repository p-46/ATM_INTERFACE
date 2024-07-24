package com.demo.atm.dto;

import java.util.List;

import com.demo.atm.entity.Transaction;

public class MiniStatementResponse {
	private String accountNumber;
    private List<Transaction> transactions;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
    

}
