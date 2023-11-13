package com.example.auth.library.exception;

public class ResourceNotFoundException extends RuntimeException{
    String  fieldValue;
    String fieldName;
    String resourceName;

    public ResourceNotFoundException(String fieldValue, String fieldName, String resourceName){
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName,fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.resourceName = resourceName;
    }


}
