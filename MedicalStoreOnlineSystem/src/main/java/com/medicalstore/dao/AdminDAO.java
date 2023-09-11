package com.medicalstore.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medicalstore.entity.Admin;
import com.medicalstore.exception.EmailNotFoundException;
import com.medicalstore.exception.IdNotFoundException;
import com.medicalstore.repository.AdminRepo;

@Repository
public class AdminDAO {

	@Autowired
	private AdminRepo repo;
	
	
	public Admin saveAdmin(Admin admin) {
		return repo.save(admin);
	}
	
	public Admin findAdminById(long id) {
		Optional<Admin> optional = repo.findById(id);
		
		if(optional.isPresent())
			return optional.get();
		
		throw new IdNotFoundException("Admin Id Not Found...");
		
	}
	/*
	public Admin deleteAdminById(long id) {
		  Admin admin = findAdminById(id);
		  repo.deleteById(id);
		  
		  return admin;
	}
	*/

	public Admin findAdminByEmail(String email){
		Optional<Admin> optional =  repo.findAdminByadminEmail(email);
			if(optional.isPresent())
				return optional.get();
			
			throw new EmailNotFoundException("Your specified email admin not found...!");
	}

	public Admin findAdminByPhoneNumber(String phoneNumber){
			Optional<Admin> optional =  repo.findAdminByPhoneNumber(phoneNumber);
			if(optional.isPresent())
				return optional.get();
			
				return null;
		
	}

}
