package com.medicalstore.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medicalstore.entity.MedicalStore;
import com.medicalstore.entity.Medicine;
import com.medicalstore.exception.IdNotFoundException;
import com.medicalstore.exception.NameNotFoundException;
import com.medicalstore.repository.MedicineRepo;

@Repository
public class MedicineDAO {

	@Autowired
	private MedicineRepo repo;
	
	public Medicine saveMedicine(Medicine medicine) {
		return repo.save(medicine);
	}
	
	public Medicine findMedicineById(long medicineId) {
		Optional<Medicine> optional = repo.findById(medicineId);
		
		if(optional.isPresent())
			return optional.get();
		
		throw new IdNotFoundException("Medicine id not found....");
	}
	
	public Medicine findMedicineByName(String medicineName) {
		Optional<Medicine> optional = repo.findByMedicineName(medicineName);
		
		if(optional.isPresent())
			return optional.get();
		
		throw new NameNotFoundException("Medicine name not found....");
	}
	
	public Medicine findMedicineByIdAndMedicalStore(long medicineId, MedicalStore medicalStore){
		Optional<Medicine> optional = repo.findByMedicineIdAndMedicalStore(medicineId,medicalStore);
		if(optional.isPresent())
			return optional.get();

		return null;	
	}

	public Medicine findMedicineBymedicineNameAndmanufacutre(String medicineName, String manufacture,MedicalStore medicalStore){
		Optional<Medicine> optional = repo.findByMedicineNameAndManufacutrerAndMedicalStore(medicineName,manufacture,medicalStore);

		if(optional.isPresent())
		   return optional.get();

		return null;
	}
	
	public Medicine deleteMedicineById(long medicineId) {
		Medicine medicine = findMedicineById(medicineId);
		repo.deleteById(medicineId);
		
		return medicine;
	}
	
	public void deleteMedicineByMedicalStore(MedicalStore medicalStore){
		
		repo.deleteAllInBatch(repo.findByMedicalStore(medicalStore));
		
	}
	
}