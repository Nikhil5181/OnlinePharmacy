package com.medicalstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicalstore.entity.Address;
import com.medicalstore.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{

    Optional<Customer> findCustomerByAddress(Address address);
    Optional<Customer> findCustomerByEmail(String email);
    Optional<Customer> findCustomerByPhoneNumber(String phoneNumber);
}
