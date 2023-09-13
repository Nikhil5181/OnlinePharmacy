package com.medicalstore.service;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicalstore.config.ResponseStructure;
import com.medicalstore.dao.AddressDAO;
import com.medicalstore.entity.Address;

@Service
public class AddressService {
	
	@Autowired
	private AddressDAO  addressDao;

	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address) {
	
		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.CREATED.value(),
															"Address Successfully Saved..",
															addressDao.saveAddress(address)),
															HttpStatus.CREATED);
				
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address address, long addressId) {
		
		addressDao.findAddressById(addressId);
		address.setAddressId(addressId);
		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.OK.value(),
															"Address Successfully Updated..",
															addressDao.saveAddress(address)),
															HttpStatus.OK);
	
	}

	public ResponseEntity<ResponseStructure<Address>> findAddressById(long addressId) {
		
		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.FOUND.value(),
															"Address Successfully Found..",
															addressDao.findAddressById(addressId)),
															HttpStatus.FOUND);

	}

	   
	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(long addressId) {
			
		return new ResponseEntity<>(new ResponseStructure<>(HttpStatus.ACCEPTED.value(),
															"Address Successfully deleted..",
															addressDao.deleteAddressById(addressId)),
															HttpStatus.ACCEPTED);
		

	}
}
