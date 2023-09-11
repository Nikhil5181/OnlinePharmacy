package com.medicalstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicalstore.entity.MedicalStore;
import com.medicalstore.entity.Medicine;

public interface MedicineRepo extends JpaRepository<Medicine,Long> {

	Optional<Medicine> findByMedicineName(String medicineName);
    Optional<Medicine> findByMedicineIdAndMedicalStore(long medicineId , MedicalStore medicalStore);
    Optional<Medicine> findByMedicineNameAndManufacutrerAndMedicalStore(String medicineName,
                                                String manufacture, MedicalStore medicalStore);

}
