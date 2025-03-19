package com.example.demo.Exception;

public class CRUDException extends RuntimeException {
    public CRUDException(String message) {
        super(message);
    }
}
