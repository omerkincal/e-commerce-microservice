package com.example.auth.domain.user.api.exception;

public class EmailAlreadyExists extends RuntimeException{
    private String message;

    public EmailAlreadyExists(String message){
        super(message);
    }

}
