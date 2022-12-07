package com.bms.devbank.exception;

import com.bms.devbank.helper.ErrorResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatus status,
                                                                  @NotNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerAlreadyExceptions.class)
    public ErrorResult handleCustomerDuplicateExceptions(CustomerAlreadyExceptions ex){
        return new ErrorResult(false,ex.getMessage(), "400");
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ErrorResult handleCustomerNotFoundException(CustomerNotFoundException ex){
        return new ErrorResult(false,ex.getMessage(), "404");
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ErrorResult handleAccountNotFoundException(AccountNotFoundException ex){
        return new ErrorResult(false,ex.getMessage(), "404");
    }

    @ExceptionHandler(AccountAmountException.class)
    public ErrorResult handleAmountLessThanZeroException(AccountAmountException ex){
        return new ErrorResult(false,ex.getMessage(), "400");
    }

    @ExceptionHandler(AccountLimitEndDateException.class)
    public ErrorResult handleAccountLimitEndDateException(AccountLimitEndDateException ex){
        return new ErrorResult(false,ex.getMessage(), "400");
    }

    @ExceptionHandler(AccountMaxLimitException.class)
    public ErrorResult handleAccountMaxLimitException(AccountMaxLimitException ex){
        return new ErrorResult(false,ex.getMessage(), "400");
    }

}
