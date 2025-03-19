package com.example.demo.Exception;

public class MissingTokenException extends RuntimeException{
    public MissingTokenException(String message){
        super(message);
    }
}
