package com.bms.devbank.controller;

import com.bms.devbank.dto.AccountDto;
import com.bms.devbank.dto.request.CreateAccountRequest;
import com.bms.devbank.dto.request.MoneyTransferRequest;
import com.bms.devbank.dto.request.UpdateAccountRequest;
import com.bms.devbank.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/account")
@Tag(name = "Account", description = "Account API")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Create account",
            description = "Create account for customer",
            tags = {"Account"})
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @Operation(summary = "Update account",
            description = "Update account for account number",
            tags = {"Account"})
    @PutMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String accountNumber,
                                                    @Valid @RequestBody UpdateAccountRequest request) {
        return ResponseEntity.ok(accountService.updateAccount(accountNumber, request));
    }

    @Operation(summary = "Delete account",
            description = "Delete account for account number",
            tags = {"Account"})
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> deleteAccountByAccountNumber(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.deleteAccount(accountNumber));
    }

    @Operation(summary = "Get all account",
            description = "Get all account",
            tags = {"Account"})
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @Operation(summary = "Get account by account number",
            description = "Get account by account number",
            tags = {"Account"})
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> getAccountByAccountNumber(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getAccountByAccountNumber(accountNumber));
    }

    @Operation(summary = "Add money to account",
            description = "Add money to account",
            tags = {"Account"})
    @PutMapping("/add/{accountNumber}")
    public ResponseEntity<AccountDto> addMoney(@PathVariable String accountNumber,
                                               @Valid @RequestParam Double amount) {
        return ResponseEntity.ok(accountService.addMoney(accountNumber, amount));
    }

    @Operation(summary = "Withdraw money from account",
            description = "Withdraw money from account",
            tags = {"Account"})
    @PutMapping("/withdraw/{accountNumber}")
    public ResponseEntity<AccountDto> withdrawMoney(@PathVariable String accountNumber,
                                                    @Valid @RequestParam Double amount) {
        return ResponseEntity.ok(accountService.withdrawMoney(accountNumber, amount));
    }

    @Operation(summary = "Transfer money from account to account",
            description = "Transfer money from account to account",
            tags = {"Account"})
    @PutMapping("/transfer")
    public ResponseEntity<AccountDto> transferMoney(@Valid @RequestBody MoneyTransferRequest request) {
        return ResponseEntity.ok(accountService.transferMoney(request));
    }

}
