package com.example.exception;

import org.springframework.http.HttpStatus;

public class BizException extends RuntimeException {

	private static final long serialVersionUID = -6782703021603400809L;
	
	private HttpStatus status = HttpStatus.BAD_REQUEST;
	
	public BizException() {
		super();
	}
	
    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BizException(String message) {
        super(message);
    }
    
    public BizException(Throwable cause) {
        super(cause);
    }

}
