package com.medicalstore.exception;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DuplicateEntryException extends RuntimeException {
    
    private Map<Object,String> errorMap;
    private String message;

    public DuplicateEntryException(Map<Object,String> errorMap){
        this.errorMap = errorMap;
    }

    public DuplicateEntryException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
    
}
