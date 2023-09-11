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
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long staffId;
	private String staffName;
	
	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String phoneNumber;
	
	@JsonIgnore
	@ManyToOne
	private Admin admin;
	
	@OneToOne
	private MedicalStore medicalStore;
}