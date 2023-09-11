package com.medicalstore.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalStoreDTO {

	private long StoreId;
	
	@Pattern(regexp = "[A-Z][a-zA-Z\\s]*", message = "invalid storeName..!")
	@NotBlank(message = "Store name can't be null....!")
	private String StoreName;
	
	@Pattern(regexp = "[A-Z][a-zA-Z\\s]*", message = "invalid manager name..!")
	@NotBlank(message = "Manager name  can't be null....!")
	private String managerName;
	
	@Pattern(regexp = "[6-9][0-9]{9}" , message = "invalid phone number..")
	@NotBlank(message = "Phone number can't be null...!")
	private String managerPhoneNumber;

}