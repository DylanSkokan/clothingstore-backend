package com.ics499.clothingstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ics499.clothingstore.model.Transaction;
import com.ics499.clothingstore.repository.TransactionRepository;

/**
 * Transaction Controller, used to send information to Angular Front end.
 * 
 * @author Dylan Skokan - Isaiah Cuellar - Tom Waterman - Justin Pham - Kyle
 *         McClernon
 *
 */

@RestController
@RequestMapping("transaction")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

	@Autowired
	TransactionRepository transactionRepository;

	@GetMapping("/test")
	public String test() {
		return "Transaction Controller";
	}

	@PostMapping("/save")
	public Transaction saveTransaction(@RequestBody Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@PostMapping("/saveMany")
	public List<Transaction> saveManyTransactions(@RequestBody List<Transaction> transactions) {
		return transactionRepository.saveAll(transactions);
	}

	@GetMapping("/all")
	public List<Transaction> list() {

		return transactionRepository.findAll();
	}

	@GetMapping("{id}")
	public Transaction get(@PathVariable Long id) {
		return transactionRepository.getReferenceById(id);
	}

	@PostMapping("/add")
	public Transaction create(@RequestBody final Transaction transaction) {
		return transactionRepository.saveAndFlush(transaction);
	}
}
