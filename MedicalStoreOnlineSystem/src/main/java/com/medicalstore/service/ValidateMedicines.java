package com.medicalstore.service;

import java.util.List;

import javax.validation.Valid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateMedicines<T> {
	
	@Valid
	private List<T> medicine;

}