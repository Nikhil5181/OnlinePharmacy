package com.medicalstore.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffDTO {

	private long staffId;
	
	@Pattern(regexp = "[A-Z][a-zA-Z\\s]*" , message = "Invalid staff name , First letter should be uppercase...")
	@NotBlank(message = "Staff name can't be null...!")
	private String staffName;
	
	@Email(message = "invalid Email....")
	private String email;
	
	@Pattern(regexp = "[6-9][0-9]{9}" , message = "Invalid mobile no. should contain 10 digit and start from between 6 to 9 number..! ")
	@NotBlank(message = "phone Number can't be null..")
	private String phoneNumber;
	
}