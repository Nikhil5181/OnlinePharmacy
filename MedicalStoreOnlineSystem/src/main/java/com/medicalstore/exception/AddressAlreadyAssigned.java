package com.medicalstore.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AddressAlreadyAssigned extends RuntimeException{

	private String message;
	
	@Override
	public String getMessage() {
		return message;
	}
}
