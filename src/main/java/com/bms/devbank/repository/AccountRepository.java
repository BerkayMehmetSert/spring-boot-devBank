package com.bms.devbank.repository;

import com.bms.devbank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Optional<Account> findByAccountNumber(String accountNumber);
}
