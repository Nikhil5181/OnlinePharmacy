package com.medicalstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicalstore.entity.Booking;
import com.medicalstore.entity.Customer;
import com.medicalstore.entity.Medicine;

public interface BookingRepo extends JpaRepository<Booking,Long> {

    List<Booking> findByMedicine(Medicine medicine);
    Optional<List<Booking>> findBookingByCustomer(Customer customer);
}
