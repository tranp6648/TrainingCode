package com.example.demo.Exception;

import com.example.demo.DTO.Response.ApiResponse;
import com.example.demo.DTO.Response.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ApiResponse response = ResponseUtil.Error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ApiResponse> handleMethodNotAllowedException(MethodNotAllowedException ex, WebRequest request) {
        ApiResponse response = ResponseUtil.ErrorStatus(405, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGlobalException(Exception ex) {
        ApiResponse response = ResponseUtil.Error("Internal Server Error: " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MissingTokenException.class)
    public ResponseEntity<ApiResponse> handleMissingTokenException(MissingTokenException ex) {
        ApiResponse response=ResponseUtil.ErrorStatus(401, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiResponse> handleInvalidTokenException(InvalidTokenException ex) {
    ApiResponse response=ResponseUtil.ErrorStatus(403, ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(CRUDException.class)
    public ResponseEntity<ApiResponse> handleCRUDException(CRUDException ex) {
        ApiResponse response=ResponseUtil.ErrorStatus(500, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
