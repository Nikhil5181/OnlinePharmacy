package com.medicalstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long adminId;
	
	@Pattern(regexp = "[A-Z][a-zA-z\\s]*" , message = "invalid admin name")
	@NotBlank(message = "name cant be blank..!")
	private String adminName;
	
	@Email(message = "invalid Email")
	@NotBlank(message = "Email can't be null....!")
	@Column(unique = true)
	private String adminEmail;
	
	@Pattern(regexp = "[6-9][0-9]{9}" , message = "Invalid mobile no. should contain 10 digit and start from between 6 to 9 number..! ")
	@NotBlank(message = "phone number cant be null....")
	@Column(unique = true)
	private String phoneNumber;
	
	@Pattern(regexp = "[\\w\\s]*[\\W][\\w\\s]*" , message ="password should be combination of Special character ,number , characeter " )
	@Length(min = 8 , message = "password should be 8 character...!")
	@NotBlank(message = "Password can't be null...!")
	private String password;
}
