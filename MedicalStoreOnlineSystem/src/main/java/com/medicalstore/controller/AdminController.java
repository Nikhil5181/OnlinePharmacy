package com.medicalstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medicalstore.config.ResponseStructure;
import com.medicalstore.dto.AdminDTO;
import com.medicalstore.entity.Admin;
import com.medicalstore.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<AdminDTO>> saveAdmin(@Validated @RequestBody Admin admin){
		return service.saveAdmin(admin);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<AdminDTO>> updateAdmin(@Validated @RequestBody Admin admin , @RequestParam long adminId){
		return service.updateAdmin(admin,adminId);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<AdminDTO>> findAdminById(@RequestParam long adminId){
		return service.findAdminById(adminId);
	}
	
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<AdminDTO>> loginAdmin(@RequestParam String email, @RequestParam String password){
		return service.login(email,password);
	}
	
	@PutMapping("/forgot")
	public ResponseEntity<ResponseStructure<AdminDTO>> forgotPassword(@RequestParam String email,@RequestParam String newPassword,@RequestParam String adminPhoneNumber){
		return service.forgotPassword(email,newPassword,adminPhoneNumber);
	}
	
}