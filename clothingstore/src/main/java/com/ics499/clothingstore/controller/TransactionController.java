package com.ics499.clothingstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ics499.clothingstore.model.Transaction;
import com.ics499.clothingstore.repository.TransactionRepository;

@RestController
public class TransactionController {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@GetMapping("/transaction")
	public String test() {
		return "Transaction Controller";
	}
	
	@PostMapping("/saveTransaction")
	public Transaction saveTransaction(@RequestBody Transaction transaction) {
		return transactionRepository.save(transaction);
	}
	
	@PostMapping("/saveManyTransactions")
	public List<Transaction> saveManyTransactions(@RequestBody List<Transaction> transactions) {
		return transactionRepository.saveAll(transactions);
	}
}
