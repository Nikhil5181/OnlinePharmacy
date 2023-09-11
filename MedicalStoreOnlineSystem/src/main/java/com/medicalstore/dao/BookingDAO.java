package com.medicalstore.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medicalstore.entity.Booking;
import com.medicalstore.entity.Customer;
import com.medicalstore.entity.Medicine;
import com.medicalstore.exception.CustomerDoesNotHaveBookingException;
import com.medicalstore.exception.IdNotFoundException;
import com.medicalstore.repository.BookingRepo;

@Repository
public class BookingDAO {

	@Autowired
	private BookingRepo repo;
	
	
	public Booking saveBooking(Booking booking) {
		return repo.save(booking);
	}
	
	
	public Booking findBookingById(long bookingId) {
		Optional<Booking> optional = repo.findById(bookingId);
		if(optional.isPresent())
			return optional.get();
		
		throw new IdNotFoundException("Booking id not found...");
	}

	// public List<Booking> findBookingByMedicine(Medicine medicine){
	// 	return repo.findByMedicine(medicine);
	// }
	
	public Booking deleteBookingById(long bookingId) {
		Booking booking = findBookingById(bookingId);
		repo.deleteById(bookingId);
		
		return booking;
	}

	public List<Booking> findBookingByCustomer(Customer customer){
		Optional<List<Booking>> optional = repo.findBookingByCustomer(customer);

		if(optional.isPresent())
			return optional.get();
		
		throw new CustomerDoesNotHaveBookingException("Customer does not have booked medicine...");	
	}
}
