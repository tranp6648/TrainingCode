package com.example.demo.Exception;

import org.springframework.web.HttpRequestMethodNotSupportedException;

public class MethodNotAllowedException extends HttpRequestMethodNotSupportedException {
    public MethodNotAllowedException(String method) {
        super(method);
    }
}
