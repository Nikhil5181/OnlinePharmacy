package com.medicalstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicalstore.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Long> {

    Optional<Admin> findAdminByPhoneNumber(String phoneNumber);
    Optional<Admin> findAdminByadminEmail(String adminEmail);
}
