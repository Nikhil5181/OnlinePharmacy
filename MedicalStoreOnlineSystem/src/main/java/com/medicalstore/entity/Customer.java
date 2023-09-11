package com.medicalstore.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	
	@Pattern(regexp = "[A-Z][a-zA-Z\\s]*" , message = "Invalid name")
	@NotBlank(message = "Customer name can't be null...!")
	private String customerName;
	
	@Email(message = "invalid email..")
	@NotBlank(message = "Email can't be null....!")
	@Column(unique = true)
	private String email;
	
	@Pattern(regexp = "[6-9][0-9]{9}" , message = "Invalid mobile no. should contain 10 digit and start from between 6 to 9 number..! ")
	@NotBlank(message = "Phone number can't be null....!")
	@Column(unique = true)
	private String phoneNumber;
	
	@Pattern(regexp = "[\\w\\s]*[\\W][\\w\\s]*" , message ="password should be combination of Special character ,number , characeter " )
	@Length(min = 8 , message = "password should be 8 character...!")
	@NotBlank(message = "Password can't be null....!")
	private String password;
	
	@OneToMany
	private List<Address> address;
	
	@JsonIgnore
	@OneToMany(mappedBy ="customer", cascade = CascadeType.REMOVE)
	private List<Booking> booking; 

}
