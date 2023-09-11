package com.medicalstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicalstore.entity.Staff;

public interface StaffRepo extends JpaRepository<Staff,Long> {

	Optional<List<Staff>> findByStaffName(String staffName);
    Optional<Staff> findByPhoneNumber(String phoneNumber);
    Optional<Staff> findByEmail(String email);

}
