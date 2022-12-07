package com.bms.devbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountMaxLimitException extends RuntimeException {
    public AccountMaxLimitException(String message) {
        super(message);
    }
}
