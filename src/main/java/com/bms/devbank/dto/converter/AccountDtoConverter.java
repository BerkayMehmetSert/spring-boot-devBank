package com.bms.devbank.dto.converter;

import com.bms.devbank.dto.AccountDto;
import com.bms.devbank.model.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {
    public AccountDto convert(Account from) {

        return AccountDto.builder()
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

    public List<AccountDto> convert(List<Account> from) {
        return from.stream().map(this::convert).collect(Collectors.toList());
    }

}
