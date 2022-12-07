package com.bms.devbank.service;

import com.bms.devbank.helper.MessageHelper;
import com.bms.devbank.dto.AccountDto;
import com.bms.devbank.dto.converter.AccountDtoConverter;
import com.bms.devbank.dto.request.CreateAccountRequest;
import com.bms.devbank.dto.request.MoneyTransferRequest;
import com.bms.devbank.dto.request.UpdateAccountRequest;
import com.bms.devbank.exception.AccountLimitEndDateException;
import com.bms.devbank.exception.AccountMaxLimitException;
import com.bms.devbank.exception.AccountNotFoundException;
import com.bms.devbank.exception.AccountAmountException;
import com.bms.devbank.helper.AccountNumberHelper;
import com.bms.devbank.helper.TimeHelper;
import com.bms.devbank.model.*;
import com.bms.devbank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter converter;

    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService,
                          AccountDtoConverter converter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.converter = converter;
    }

    public AccountDto createAccount(CreateAccountRequest request) {
        Customer customer = customerService.findCustomerById(request.getCustomerId());
        if (customer.getAccounts().size() > 5) {
            throw new AccountMaxLimitException(MessageHelper.ACCOUNT_MAX_LIMIT);
        }
        Account account = Account.builder()
                .accountNumber(AccountNumberHelper.generateAccountNumber())
                .accountType(request.getAccountType())
                .isActive(true)
                .balance(0.0)
                .accountStatus(AccountStatus.ACTIVE)
                .customer(customer)
                .accountLimitType(request.getAccountLimitType())
                .accountLimitIsActive(true)
                .accountLimitStartDate(TimeHelper.startDate())
                .accountLimitCreatedAt(TimeHelper.localDateTime())
                .accountLimitStatus(AccountLimitStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();

        switch (request.getAccountLimitType()) {
            case DAILY -> {
                account.setAccountLimitEndDate(TimeHelper.endDate(1));
                account.setAccountLimit(1000.0);
                account.setAccountLimitAmount(1000.0);
            }
            case WEEKLY -> {
                account.setAccountLimitEndDate(TimeHelper.endDate(8));
                account.setAccountLimit(5000.0);
                account.setAccountLimitAmount(5000.0);
            }
            case MONTHLY -> {
                account.setAccountLimitEndDate(TimeHelper.endDate(31));
                account.setAccountLimit(20000.0);
                account.setAccountLimitAmount(20000.0);
            }
            case YEARLY -> {
                account.setAccountLimitEndDate(TimeHelper.endDate(366));
                account.setAccountLimit(100000.0);
                account.setAccountLimitAmount(100000.0);
            }
        }

        return converter.convert(accountRepository.save(account));
    }

    public AccountDto updateAccount(String accountNumber, UpdateAccountRequest request) {
        Account account = findAccountByAccountNumber(accountNumber);
        account.setAccountType(request.getAccountType());
        account.setAccountLimitType(request.getAccountLimitType());
        account.setUpdatedAt(TimeHelper.localDateTime());
        return converter.convert(accountRepository.save(account));
    }

    public AccountDto deleteAccount(String accountNumber) {
        Account account = findAccountByAccountNumber(accountNumber);

        account.setIsActive(false);
        account.setDeletedAt(TimeHelper.localDateTime());
        account.setAccountStatus(AccountStatus.INACTIVE);
        account.setAccountLimitIsActive(false);
        account.setAccountLimitDeletedAt(TimeHelper.localDateTime());
        account.setAccountLimitStatus(AccountLimitStatus.INACTIVE);
        account.setAccountLimitDeletedAt(TimeHelper.localDateTime());

        return converter.convert(accountRepository.save(account));
    }

    public List<AccountDto> getAllAccounts() {
        return converter.convert(accountRepository.findAll());
    }

    public AccountDto getAccountByAccountNumber(String accountNumber) {
        return converter.convert(findAccountByAccountNumber(accountNumber));
    }

    public AccountDto addMoney(String accountNumber, Double amount) {
        Account account = findAccountByAccountNumber(accountNumber);
        if (amount < 0) {
            throw new AccountAmountException(MessageHelper.AMOUNT_MUST_BE_GREATER_THAN_ZERO);
        }

        account.setBalance(account.getBalance() + amount);
        account.setUpdatedAt(TimeHelper.localDateTime());

        return converter.convert(accountRepository.save(account));
    }

    public AccountDto withdrawMoney(String accountNumber, Double amount) {
        Account account = findAccountByAccountNumber(accountNumber);
        checkAccountAmount(account, amount);
        checkAccountLimitEndDate(account);

        account.setAccountLimit(account.getAccountLimit() - amount);
        account.setAccountLimitAmount(account.getAccountLimitAmount() - amount);
        account.setBalance(account.getBalance() - amount);
        account.setUpdatedAt(TimeHelper.localDateTime());

        return converter.convert(accountRepository.save(account));
    }

    public AccountDto transferMoney(MoneyTransferRequest request) {
        Account fromAccount = findAccountByAccountNumber(request.getFromAccountNumber());
        Account toAccount = findAccountByAccountNumber(request.getToAccountNumber());

        checkAccountAmount(fromAccount, request.getAmount());
        checkAccountLimitEndDate(fromAccount);

        if (!fromAccount.getCustomer().getId().equals(toAccount.getCustomer().getId())) {
            final Double commission = request.getAmount() * 0.01;
            fromAccount.setBalance(fromAccount.getBalance() - request.getAmount() - commission);
            fromAccount.setAccountLimit(fromAccount.getAccountLimit() - request.getAmount() - commission);
            fromAccount.setAccountLimitAmount(fromAccount.getAccountLimitAmount() - request.getAmount() - commission);
            fromAccount.setUpdatedAt(TimeHelper.localDateTime());
        } else {
            fromAccount.setAccountLimit(fromAccount.getAccountLimit() - request.getAmount());
            fromAccount.setAccountLimitAmount(fromAccount.getAccountLimitAmount() - request.getAmount());
            fromAccount.setBalance(fromAccount.getBalance() - request.getAmount());
            fromAccount.setUpdatedAt(TimeHelper.localDateTime());
        }
        toAccount.setBalance(toAccount.getBalance() + request.getAmount());
        toAccount.setUpdatedAt(TimeHelper.localDateTime());

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return converter.convert(fromAccount);
    }

    private Account findAccountByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() ->
                new AccountNotFoundException(MessageHelper.ACCOUNT_NOT_FOUND + accountNumber));
        if (!account.getIsActive()) {
            throw new AccountNotFoundException(MessageHelper.ACCOUNT_NOT_FOUND + accountNumber);
        }
        return account;
    }

    private void checkAccountLimitEndDate(Account account) {
        if (account.getAccountLimitEndDate().isBefore(LocalDate.now())) {
            account.setAccountLimit(0.0);
            account.setAccountLimitAmount(0.0);
            account.setAccountLimitStatus(AccountLimitStatus.INACTIVE);
            account.setAccountLimitIsActive(false);
            account.setAccountLimitDeletedAt(TimeHelper.localDateTime());

            throw new AccountLimitEndDateException(
                    MessageHelper.ACCOUNT_LIMIT_END_DATE + account.getAccountLimitEndDate()
            );
        }
        if (account.getAccountLimitEndDate().isEqual(LocalDate.now())) {
            account.setAccountLimit(0.0);
            account.setAccountLimitAmount(0.0);
            account.setAccountLimitStatus(AccountLimitStatus.INACTIVE);
            account.setAccountLimitIsActive(false);
            account.setAccountLimitDeletedAt(TimeHelper.localDateTime());

            throw new AccountLimitEndDateException(
                    MessageHelper.ACCOUNT_LIMIT_END_DATE + account.getAccountLimitEndDate()
            );
        }
    }

    private void checkAccountAmount(Account account, Double amount) {
        if (amount < 0) {
            throw new AccountAmountException(MessageHelper.AMOUNT_MUST_BE_GREATER_THAN_ZERO);
        }
        if (account.getAccountLimit() <= amount) {
            throw new AccountAmountException(MessageHelper.ACCOUNT_LIMIT + account.getAccountLimit());
        }

        if (account.getAccountLimitAmount() <= amount) {
            throw new AccountAmountException(MessageHelper.ACCOUNT_LIMIT_AMOUNT + account.getAccountLimitAmount());
        }

        if (account.getBalance() < amount) {
            throw new AccountAmountException(MessageHelper.ACCOUNT_BALANCE + account.getBalance());
        }
    }

}
