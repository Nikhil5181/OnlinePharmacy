package com.medicalstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medicalstore.config.ResponseStructure;
import com.medicalstore.dto.BookingDTO;
import com.medicalstore.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService service;
	
	@PostMapping("{customerId}/{medicalStoreId}")
	public ResponseEntity<ResponseStructure<BookingDTO>> createBooking(@RequestBody BookingDTO bookingDto , @PathVariable long customerId, @PathVariable long medicalStoreId ,@RequestParam Map<String,String> medicinesIdAndQty){
		return service.saveBooking(bookingDto,customerId,medicalStoreId,medicinesIdAndQty);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<BookingDTO>> cancelBooking(@RequestParam long bookingId){
		return service.cancelBooking(bookingId);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<BookingDTO>>> getBookings(@RequestParam long customerId){
		return service.getBookings(customerId);
	}
}
