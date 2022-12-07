package com.bms.devbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountAmountException extends RuntimeException {
    public AccountAmountException(String message) {
        super(message);
    }
}
