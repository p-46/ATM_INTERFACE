package com.demo.atm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.atm.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	
	@Query(value="select * from Transaction where account_number = :accountNumber order by transaction_id desc limit 10", nativeQuery=true)
	List<Transaction> getTransactionsByAccountNumber(String accountNumber);

}
