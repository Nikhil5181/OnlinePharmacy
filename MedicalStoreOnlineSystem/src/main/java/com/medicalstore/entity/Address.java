package com.medicalstore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressId;
	
	@Pattern(regexp = "[A-Z][\\w\\s]*" , message = "invalid Street name..!")
	@NotBlank(message = "streetName can't be null")
	private String streetName;
	
	@Pattern(regexp = "[A-Z][a-zA-Z\\s]*" , message = "invalid city name...!")
	@NotBlank(message = "City name can't be null")
	private String city;
	
	@Pattern(regexp = "[A-Z][a-zA-Z\\s]*" , message = "invalid state name...!")
	@NotBlank(message = "State name can't be null")
	private String state;
	
	@NotNull(message = "pincode can't be null...!")
	@Min(value = 111111 , message = "Invalid pincode...!")
	private long pincode;		
}
