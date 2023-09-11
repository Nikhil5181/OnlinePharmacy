package com.medicalstore.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medicalstore.entity.Address;
import com.medicalstore.exception.IdNotFoundException;
import com.medicalstore.repository.AddressRepo;

@Repository
public class AddressDAO {

	@Autowired
	private AddressRepo repo;
	
	public Address saveAddress(Address address) {
		return repo.save(address);
	}
	
	public Address findAddressById(long addressId) {
		Optional<Address> optional =  repo.findById(addressId);
		if(optional.isPresent())
			return optional.get();
		
		throw new IdNotFoundException("Address Id not Found....");
	}
	
	
	public Address deleteAddressById(long addressId) {
		  Address address = findAddressById(addressId);
		  repo.deleteById(addressId);
		  
		  return address;
	}
	
}
