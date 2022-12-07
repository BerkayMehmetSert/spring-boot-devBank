package com.bms.devbank.dto.converter;

import com.bms.devbank.dto.AddressDto;
import com.bms.devbank.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoConverter {
    public AddressDto convert(Address from){
        return AddressDto.builder()
                .addressLine(from.getAddressLine())
                .city(from.getCity())
                .state(from.getState())
                .country(from.getCountry())
                .zipCode(from.getZipCode())
                .build();
    }
}
