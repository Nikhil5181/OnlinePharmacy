package com.medicalstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicalstore.entity.MedicalStore;

public interface MedicalStoreRepo extends JpaRepository<MedicalStore,Long> {

	Optional<MedicalStore> findBystoreName(String medicalName);

    Optional<MedicalStore> findByManagerPhoneNumber(String phoneNumber);

}
