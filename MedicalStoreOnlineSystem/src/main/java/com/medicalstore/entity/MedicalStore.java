package com.medicalstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MedicalStore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long storeId;
	private String storeName;
	private String managerName;
	
	@Column(unique = true)
	private String managerPhoneNumber;

	@JsonIgnore
	@ManyToOne
	private Admin admin;
	
	@OneToOne
	private Address address;
	
}