package com.medicalstore.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseStructure<T> {
	
	private int status;
	private String message;
	private T data;
		
}