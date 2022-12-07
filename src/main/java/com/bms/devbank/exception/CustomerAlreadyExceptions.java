package com.bms.devbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CustomerAlreadyExceptions extends RuntimeException {
    public CustomerAlreadyExceptions(String message) {
        super(message);
    }
}