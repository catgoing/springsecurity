package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BizException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(BizException e){
        return new ResponseEntity<ErrorResponse>((HttpStatusCode) new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), "e101"));
    }
}