package com.medicalstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicalstore.entity.Address;

public interface AddressRepo extends JpaRepository<Address,Long> {

}
