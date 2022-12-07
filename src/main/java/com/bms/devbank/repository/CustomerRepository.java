package com.bms.devbank.repository;

import com.bms.devbank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByNationalId(String nationalId);

    boolean existsByEmail(String email);

    boolean existsByNationalId(String nationalId);

    boolean existsByPhone(String phone);

    boolean existsByFirstNameIgnoreCase(String firstName);

}
