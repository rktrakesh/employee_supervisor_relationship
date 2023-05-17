package com.acnovate.exceptions;

import com.acnovate.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(Exception ex){
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
