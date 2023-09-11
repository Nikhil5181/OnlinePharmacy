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
import com.medicalstore.entity.Address;
import com.medicalstore.service.AddressService;

@RestController
@RequestMapping("/Address")
public class AddrerssController {

	@Autowired
	private AddressService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@Valid @RequestBody Address address){
		return service.saveAddress(address);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@Valid @RequestBody Address address ,@RequestParam long addressId){
		return service.updateAddress(address,addressId);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Address>> findAddressById(@RequestParam long addressId){
		return service.findAddressById(addressId);
	}
	
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(@RequestParam long addressId){
		return service.deleteAddressById(addressId);
	}
	

}