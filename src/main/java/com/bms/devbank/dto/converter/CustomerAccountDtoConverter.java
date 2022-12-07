package com.bms.devbank.dto.converter;

import com.bms.devbank.dto.CustomerAccountDto;
import com.bms.devbank.model.Account;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerAccountDtoConverter {
    public CustomerAccountDto convert(Account from){
        return CustomerAccountDto.builder()
                .accountNumber(from.getAccountNumber())
                .balance(from.getBalance())
                .isActive(from.getIsActive())
                .accountType(from.getAccountType())
                .accountStatus(from.getAccountStatus())
                .accountLimit(from.getAccountLimit())
                .accountLimitType(from.getAccountLimitType())
                .accountLimitStatus(from.getAccountLimitStatus())
                .accountLimitAmount(from.getAccountLimitAmount())
                .accountLimitStartDate(from.getAccountLimitStartDate())
                .accountLimitEndDate(from.getAccountLimitEndDate())
                .build();
    }

    public List<CustomerAccountDto> convert(List<Account> from){
        return from.stream().map(this::convert).collect(java.util.stream.Collectors.toList());
    }
}
