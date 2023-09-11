package com.medicalstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medicalstore.config.ResponseStructure;
import com.medicalstore.dto.CustomerDTO;
import com.medicalstore.entity.Customer;
import com.medicalstore.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;
	

	@PostMapping
	public ResponseEntity<ResponseStructure<CustomerDTO>> saveCustomer(@Valid @RequestBody Customer customer,
																		@RequestParam long addressId) {

		return service.saveCustomer(customer, addressId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<CustomerDTO>> updateCustomer(@Valid @RequestBody Customer customer,
			@RequestParam long customerId, @RequestParam long addressId) {

		return service.updateCustomer(customer, customerId, addressId);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<CustomerDTO>> findCustomerById(@RequestParam long customerId) {

		return service.findCustomerById(customerId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<CustomerDTO>> deleteCustomerById(@RequestParam long customerId) {

		return service.deleteCustomerById(customerId);
	}

	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<CustomerDTO>> login(@RequestParam String customerEmail , @RequestParam String customerPassword){
		
		return service.login(customerEmail,customerPassword);
	}

	@GetMapping("/forgot")
	public ResponseEntity<ResponseStructure<CustomerDTO>> login(@RequestParam String customerEmail,@RequestParam String newPassword,@RequestParam String customerPassword){		
		return service.forgotPassword(customerEmail,newPassword,customerPassword);
	}
	

}
