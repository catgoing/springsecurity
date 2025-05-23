package com.example.exception;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

	private HttpStatus status;
    private String code;
    private String message;

    public ErrorResponse(HttpStatus status, String code, String message) {
    	this.status = status;
        this.message = message;
        this.code = code;
    }

}