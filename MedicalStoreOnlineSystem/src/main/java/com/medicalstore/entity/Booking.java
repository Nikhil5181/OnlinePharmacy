package com.medicalstore.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.medicalstore.enums.BookingStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookingId;
	private LocalDate orderDate;
	private int quantity;
	private String paymentMode;
	private LocalDate expectedDate;
	private BookingStatus status;
	private Long[][] medicine; 

	// @ManyToMany
	// private List<Medicine> medicine;
	
	@ManyToOne
	@JoinColumn
	private Customer customer;
}