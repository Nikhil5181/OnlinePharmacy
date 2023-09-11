package com.medicalstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medicalstore.config.ResponseStructure;
import com.medicalstore.dto.StaffDTO;
import com.medicalstore.entity.Staff;
import com.medicalstore.service.StaffService;

@RestController
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private StaffService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Staff>> saveStaff(@Valid @RequestBody StaffDTO staff,@RequestParam long adminId ,@RequestParam long medicalStoreId){	
		
		return service.saveStaff(staff,adminId,medicalStoreId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Staff>> updateStaff(@Valid @RequestBody StaffDTO staff,@RequestParam long staffId , @RequestParam long adminId){	
		
		return service.updateStaff(staff,staffId,adminId);
	}
	
	@GetMapping("/id")
	public ResponseEntity<ResponseStructure<Staff>> findStaffById(@RequestParam long staffId){
		return service.findStaffById(staffId);
	}
	
	@GetMapping("/name")
	public ResponseEntity<ResponseStructure<List<Staff>>> findStaffByName(@RequestParam String staffName){
		return service.findStaffByName(staffName);
	}
	                                                                                         
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Staff>> deleteStaffById(@RequestParam long staffId , @RequestParam long adminId){
		return service.deleteStaffById(staffId,adminId);
	}
		
}
