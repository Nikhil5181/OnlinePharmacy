package com.medicalstore.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MedicineNotAvailableException extends RuntimeException {

	private String message;
	
	@Override
	public String getMessage() {
		return message;
	}
}
