package com.medicalstore.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medicalstore.entity.Address;
import com.medicalstore.entity.Customer;
import com.medicalstore.exception.EmailNotFoundException;
import com.medicalstore.exception.IdNotFoundException;
import com.medicalstore.repository.CustomerRepo;

@Repository
public class CustomerDAO {

	@Autowired
	private CustomerRepo repo;
	
	
	public Customer saveCustomer(Customer customer) {
		return repo.save(customer);
	}
	
	
	public Customer findCutomerById(long customerId) {
		Optional<Customer> optional = repo.findById(customerId);
		
		if(optional.isPresent())
			return optional.get();
		
		throw new IdNotFoundException("Customer id not found....");
	}
	
	public Customer deleteCustomerById(long customerId) {
		Customer customer = findCutomerById(customerId);
		repo.deleteById(customerId);
		
		return customer;
	}

	public Customer findCustomerByAddress(Address address){
		Optional<Customer> optional = repo.findCustomerByAddress(address);

		if(optional.isPresent())
			return optional.get();

		return null;	
	}

	public Customer findCustomerByEmail(String email){
		Optional<Customer> optional = repo.findCustomerByEmail(email);
		if(optional.isPresent())
			return optional.get();
		
		throw new EmailNotFoundException("Your specified email customer not found....");	
	}

	public Customer findCustomerByPhoneNumber(String phoneNumber){
		Optional<Customer> optional = repo.findCustomerByPhoneNumber(phoneNumber);
		if(optional.isPresent())
			return optional.get();
		
			return null;
	}

}
