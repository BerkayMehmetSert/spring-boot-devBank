package com.bms.devbank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyTransferRequest {
    private String fromAccountNumber;
    private String toAccountNumber;
    private Double amount;
}
