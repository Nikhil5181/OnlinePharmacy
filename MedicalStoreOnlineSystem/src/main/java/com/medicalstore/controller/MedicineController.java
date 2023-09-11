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
import com.medicalstore.dto.MedicineDTO;
import com.medicalstore.entity.Medicine;
import com.medicalstore.service.MedicineService;
import com.medicalstore.service.ValidateMedicines;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
	private MedicineService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<List<Medicine>>> saveMedicines(@Valid @RequestBody ValidateMedicines<MedicineDTO> medicineDto, @RequestParam long medicalStoreId , @RequestParam long staffId){
	
		return service.saveMedicines(medicineDto.getMedicine(),medicalStoreId,staffId);	
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Medicine>> updateMedicine(@Valid @RequestBody MedicineDTO medicineDto , @RequestParam long medicineId, @RequestParam long staffId){
		
		return service.updateMedicine(medicineDto,medicineId,staffId);
	}
	
	@GetMapping("/id")
	public ResponseEntity<ResponseStructure<Medicine>> findMedicineById(@RequestParam long medicineId){
	
		return service.findMedicineById(medicineId);
	}
	
	@GetMapping("/name")
	public ResponseEntity<ResponseStructure<Medicine>> findMedicineByName(@RequestParam String medicineName){
	
		return service.findMedicineByName(medicineName);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Medicine>> deleteMedicineById(@RequestParam long medicineId, @RequestParam long staffId){
		
		return service.deleteMedicineById(medicineId,staffId);
	}
	
}