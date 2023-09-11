package com.medicalstore.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medicalstore.entity.Staff;
import com.medicalstore.exception.IdNotFoundException;
import com.medicalstore.exception.NameNotFoundException;
import com.medicalstore.repository.StaffRepo;

@Repository
public class StaffDAO {

	@Autowired
	private StaffRepo repo;
	
	
	public Staff saveStaff(Staff staff) {
		return repo.save(staff);
	}
	
	
	public Staff findStaffById(long staffId) {
		Optional<Staff> optional = repo.findById(staffId);
		
		if(optional.isPresent())
			return optional.get();
		
		throw new IdNotFoundException("Staff id not found...");
		
	}
	
	
	public List<Staff> findStaffByName(String staffName) {
        Optional<List<Staff>> optional = repo.findByStaffName(staffName);
		
		if(optional.isPresent())
			return optional.get();
		
		throw new NameNotFoundException("Staff name not found...");
		
	}
	
	
	public Staff deleteStaffById(long staffId) {
		Staff staff = findStaffById(staffId);
		repo.deleteById(staffId);
		
		return staff;
				
	}
	
	public List<Staff> getAllStaff(){
		return repo.findAll();
	}


    public Staff findStaffByEmail(String email) {
        Optional<Staff> optional = repo.findByEmail(email);
		if(optional.isPresent())
			return optional.get();

			return null;
    }


    public Staff findStaffByPhoneNumber(String phoneNumber) {
		Optional<Staff> optional = repo.findByPhoneNumber(phoneNumber);
		if(optional.isPresent())
			return optional.get();

			return null;
    }
}