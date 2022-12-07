package com.bms.devbank.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String nationalId;
    private String email;
    private String password;
    private String phone;
    private Boolean isActive;
    private AddressDto address;
    private List<CustomerAccountDto> accounts;

}
