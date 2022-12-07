package com.bms.devbank.dto;

import com.bms.devbank.model.AccountLimitStatus;
import com.bms.devbank.model.AccountLimitType;
import com.bms.devbank.model.AccountStatus;
import com.bms.devbank.model.AccountType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AccountDto {
    private String accountNumber;
    private Double balance;
    private Boolean isActive;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private Double accountLimit;
    private AccountLimitType accountLimitType;
    private AccountLimitStatus accountLimitStatus;
    private Double accountLimitAmount;
    private LocalDate accountLimitStartDate;
    private LocalDate accountLimitEndDate;

}
