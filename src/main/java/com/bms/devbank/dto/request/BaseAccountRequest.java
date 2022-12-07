package com.bms.devbank.dto.request;

import com.bms.devbank.model.AccountLimitType;
import com.bms.devbank.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseAccountRequest {
    private AccountType accountType;
    private AccountLimitType accountLimitType;
}
