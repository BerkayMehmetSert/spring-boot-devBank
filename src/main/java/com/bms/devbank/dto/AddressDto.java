package com.bms.devbank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private String addressLine;
    private String city;
    private String state;
    private String country;
    private String zipCode;

}
