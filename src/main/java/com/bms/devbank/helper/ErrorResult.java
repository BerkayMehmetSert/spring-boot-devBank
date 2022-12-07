package com.bms.devbank.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResult {
    private Boolean isSuccess;
    private String message;
    private String code;

}
