package com.medicalstore.controller;

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
import com.medicalstore.dto.MedicalStoreDTO;
import com.medicalstore.entity.MedicalStore;
import com.medicalstore.service.MedicalStoreService;

@RestController
@RequestMapping("/medicalStore")
public class MedicalStoreController {

	@Autowired
	private MedicalStoreService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<MedicalStore>> saveMedicalStore(@Valid @RequestBody MedicalStoreDTO medicalStore,
																@RequestParam long adminId ,@RequestParam long addressId){
		
			return service.saveMedicalStore(medicalStore,adminId,addressId);
		
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<MedicalStore>> updateMedicalStore(@Valid @RequestBody MedicalStoreDTO medicalStore,
																			  @RequestParam long medicalStoreId,@RequestParam long adminId){

			return service.updateMedicalStore(medicalStore,medicalStoreId,adminId);
	}
	
	@GetMapping("/id")
	public ResponseEntity<ResponseStructure<MedicalStore>> findMedicalStoreById(@RequestParam long medicalStoreId){
		
			return service.findMedicalStoreById(medicalStoreId);
	}
	
	@GetMapping("/name")
	public ResponseEntity<ResponseStructure<MedicalStore>> findMedicalStoreByName(@RequestParam String medicalStoreName){

			return service.findMedicalStoreByName(medicalStoreName);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MedicalStore>> deleteMedicalStoreById(@RequestParam long medicalStoreId,@RequestParam long adminId){
	
			return service.deleteMedicalStoreById(medicalStoreId,adminId);
	}

}