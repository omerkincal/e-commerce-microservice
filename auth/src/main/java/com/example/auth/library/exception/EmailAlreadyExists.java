package com.example.auth.library.exception;

public class EmailAlreadyExists extends RuntimeException{
    private String message;

    public EmailAlreadyExists(String message){
        super(message);
    }

}
