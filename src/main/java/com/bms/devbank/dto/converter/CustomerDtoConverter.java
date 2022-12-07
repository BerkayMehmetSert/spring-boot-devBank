package com.bms.devbank.dto.converter;

import com.bms.devbank.dto.CustomerDto;
import com.bms.devbank.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDtoConverter {
    private final AddressDtoConverter addressDtoConverter;
    private final CustomerAccountDtoConverter customerAccountDtoConverter;

    public CustomerDtoConverter(AddressDtoConverter addressDtoConverter,
                                CustomerAccountDtoConverter customerAccountDtoConverter) {
        this.addressDtoConverter = addressDtoConverter;

        this.customerAccountDtoConverter = customerAccountDtoConverter;
    }

    public CustomerDto convert(Customer from){
        if (from.getAccounts() == null) {
            return CustomerDto.builder()
                    .firstName(from.getFirstName())
                    .lastName(from.getLastName())
                    .nationalId(from.getNationalId())
                    .email(from.getEmail())
                    .password(from.getPassword())
                    .phone(from.getPhone())
                    .isActive(from.getIsActive())
                    .address(addressDtoConverter.convert(from.getAddress()))
                    .build();
        }
        return CustomerDto.builder()
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .nationalId(from.getNationalId())
                .email(from.getEmail())
                .password(from.getPassword())
                .phone(from.getPhone())
                .isActive(from.getIsActive())
                .address(addressDtoConverter.convert(from.getAddress()))
                .accounts(customerAccountDtoConverter.convert(from.getAccounts()))
                .build();

    }
    public List<CustomerDto> convert(List<Customer> from){
        return from.stream().map(this::convert).toList();
    }
}
