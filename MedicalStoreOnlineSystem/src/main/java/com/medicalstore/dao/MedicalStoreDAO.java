package com.medicalstore.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medicalstore.entity.MedicalStore;
import com.medicalstore.exception.IdNotFoundException;
import com.medicalstore.exception.NameNotFoundException;
import com.medicalstore.repository.MedicalStoreRepo;

@Repository
public class MedicalStoreDAO {

	@Autowired
	private MedicalStoreRepo repo;
	
	public MedicalStore saveMedicalStore(MedicalStore medicalStore) {
		return repo.save(medicalStore);
	}
	
	
	public MedicalStore findMedicalStoreById(long medicalId) {
	  Optional<MedicalStore> optional = repo.findById(medicalId);
	  
	  if(optional.isPresent())
		  return optional.get();
	  
	  throw new IdNotFoundException("MedicalStore id not found.....");
	}
	
	public MedicalStore findMedicalStoreByName(String medicalName) {
	  Optional<MedicalStore> optional = repo.findBystoreName(medicalName);
	  
	  if(optional.isPresent())
		  return optional.get();
	  
	  throw new NameNotFoundException("MedicalStore name not found....");
	}
	

	public MedicalStore deleteMedicalStoreById(long medicalId) {
		MedicalStore medicalStore = findMedicalStoreById(medicalId);
		repo.deleteById(medicalId);
		
		return medicalStore;
	}
	
	public List<MedicalStore> getAllMedicalStore(){
		
		return repo.findAll();
	}


    public MedicalStore findMedicalByManagerPhone(String phoneNumber) {
		Optional<MedicalStore> optional = repo.findByManagerPhoneNumber(phoneNumber);
		if(optional.isPresent())
			return optional.get();

			return null;
    }
}