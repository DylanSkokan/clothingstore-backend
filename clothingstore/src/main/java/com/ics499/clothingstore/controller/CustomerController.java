package com.ics499.clothingstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ics499.clothingstore.model.Customer;
import com.ics499.clothingstore.repository.CustomerRepository;
import com.ics499.clothingstore.service.CustomerService;
import com.ics499.clothingstore.serviceImp.CustomerServiceImp;

/**
 * Customer Controller, used to send information to Angular Front end.
 * 
 * @author Dylan Skokan - Isaiah Cuellar - Tom Waterman - Justin Pham - Kyle
 *         McClernon
 *
 */

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CustomerServiceImp test;
	@Autowired
	CustomerService customerService;

	public String customerPage() {
		return "Customer Page";

	}

	@GetMapping("/test")
	public String test() {

		test.isValid("123@email.com", "abc123");
		return "Customer Controller";
	}

	@PostMapping("/login")
	public Customer login(@RequestBody Map<String, String> customerInformation) {
		if (customerService.isValid(customerInformation.get("username"), customerInformation.get("password"))) {
			return customerRepository.findByUsername(customerInformation.get("username"));
		} else {
			return null;
		}
	}

	@PostMapping("/changeAccountInfo")
	@Transactional
	public boolean changeAccountInfo(@RequestBody Map<String, String> customerInformation) {

		// get id of customer
		Customer cust = customerRepository.findByUsername(customerInformation.get("oldUsername"));
		if (cust != null) {
			Long custId = cust.getUserId();
			// update customer repo with new info
			customerRepository.updateCustomer(custId, customerInformation.get("username"),
					customerInformation.get("password"), customerInformation.get("firstName"),
					customerInformation.get("lastName"), customerInformation.get("email"));
			return true;
		} else {
			return false;
		}
	}

	@PostMapping("/getCustomer")
	public Customer getCustomerByUsername(@RequestBody Map<String, String> customerInformation) {
		Customer cust = customerRepository.findByUsername(customerInformation.get("username"));
		return cust;
	}

	@PostMapping("/createCustomer")
	public boolean createCustomer(@RequestBody Map<String, String> customerInformation) {
		// username, password, firstname, lastname, email
		if (customerService.createCustomer(customerInformation.get("username"), customerInformation.get("password"),
				customerInformation.get("firstName"), customerInformation.get("lastName"),
				customerInformation.get("email"))) {
			return true;
		} else {
			return false;
		}
	}

	@PostMapping("/save")
	public Customer saveCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@PostMapping("/saveMany")
	public List<Customer> saveManyCustomers(@RequestBody List<Customer> customers) {
		return customerRepository.saveAll(customers);
	}

	@GetMapping("/all")
	public List<Customer> list() {
		return customerRepository.findAll();
	}

	@GetMapping("{id}")
	public Customer get(@PathVariable Long id) {
		return customerRepository.getReferenceById(id);
	}

	@GetMapping("/userAccount/{username}")
	public Customer getByUsername(@PathVariable("username") String username) {
		Customer cust = customerRepository.findByUsername(username);
		return cust;
	}

	@PostMapping("/add")
	public Customer create(@RequestBody final Customer customer) {
		return customerRepository.saveAndFlush(customer);
	}

}
