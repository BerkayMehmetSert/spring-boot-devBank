package com.bms.devbank.dto.request;

import com.bms.devbank.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private AddressDto address;
}
