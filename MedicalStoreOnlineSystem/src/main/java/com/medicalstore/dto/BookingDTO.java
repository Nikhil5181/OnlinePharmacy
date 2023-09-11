package com.medicalstore.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.medicalstore.enums.BookingStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookingDTO {

	private long bookingId;

	@NotNull(message = "orderdate can't be null...")
	private LocalDate orderDate;

	private int quantity;
	
	@NotBlank(message = "Paymentmode can't be null..")
	private String paymentMode;

	@NotNull(message = "expected date can't be null...")
	private LocalDate expectedDate;

	private BookingStatus status;
	//When sending data back to the front end that time i will send medicineId and medicineQty
	private Long[][] medicineDto;
	private CustomerDTO customerDto;
	
}