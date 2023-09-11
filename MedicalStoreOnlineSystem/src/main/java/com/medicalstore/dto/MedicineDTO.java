package com.medicalstore.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

 
@Getter
@Setter
public class MedicineDTO implements Comparable {
	
	private long medicineId;
	
	@Pattern(regexp = "[\\w\\s]*" , message = "invalid medicine name...!")
	@NotBlank(message = "Medicine name can't be null...")
	private String medicineName;
	
	@NotNull(message = "cost cant be null..!")
	private double cost;
	
	@NotNull(message = "date cant be null..!")
	private LocalDate expiryDate;
	
	@NotNull(message = "stockQuantity cant be null...!")
	private int stockQuantity;
	
	@Pattern(regexp = "[a-zA-Z]*",message = "Manufacture name contain only character...")
	@NotBlank(message = "manufacturer cant be null....")
	private String manufacutrer;
	
	@Pattern(regexp = "[a-zA-Z]*" , message = "Description contain only characters...")
	private String description;

	

	@Override
	public boolean equals(Object object){

		MedicineDTO medicine = (MedicineDTO)object;

		if(this.medicineName.equalsIgnoreCase(medicine.medicineName) &&
		   this.manufacutrer.equalsIgnoreCase(medicine.manufacutrer) &&
		   this.expiryDate.equals(medicine.expiryDate)){
				return true;
			}
		
		return false;	
	}

	@Override
	public int compareTo(Object object){

		MedicineDTO medicine = (MedicineDTO)object;
		
		if(this.equals(medicine))
			return 0;

		return 1;	
	}
}