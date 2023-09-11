package com.medicalstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

	private long addressId;
	private String streetName;
	private String city;
	private String state;
	private long pincode;
		
}
