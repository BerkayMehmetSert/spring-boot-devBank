package com.bms.devbank.service;

import com.bms.devbank.helper.MessageHelper;
import com.bms.devbank.dto.CustomerDto;
import com.bms.devbank.dto.converter.CustomerDtoConverter;
import com.bms.devbank.dto.request.CreateCustomerRequest;
import com.bms.devbank.dto.request.UpdateCustomerRequest;
import com.bms.devbank.exception.CustomerAlreadyExceptions;
import com.bms.devbank.exception.CustomerNotFoundException;
import com.bms.devbank.helper.TimeHelper;
import com.bms.devbank.model.AccountLimitStatus;
import com.bms.devbank.model.AccountStatus;
import com.bms.devbank.model.Address;
import com.bms.devbank.model.Customer;
import com.bms.devbank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    public CustomerDto createCustomer(CreateCustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .nationalId(request.getNationalId())
                .email(request.getEmail())
                .password(request.getPassword())
                .phone(request.getPhone())
                .address(Address.builder()
                        .addressLine(request.getAddress().getAddressLine())
                        .city(request.getAddress().getCity())
                        .state(request.getAddress().getState())
                        .country(request.getAddress().getCountry())
                        .zipCode(request.getAddress().getZipCode())
                        .isActive(true)
                        .createdAt(TimeHelper.localDateTime())
                        .build())
                .isActive(true)
                .createdAt(TimeHelper.localDateTime())
                .build();

        existsCustomer(customer);

        return converter.convert(customerRepository.save(customer));
    }

    public CustomerDto updateCustomer(Integer id, UpdateCustomerRequest request) {
        Customer customer = findCustomerById(id);
        customerIsActive(customer);

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPassword(request.getPassword());
        customer.setPhone(request.getPhone());
        customer.getAddress().setAddressLine(request.getAddress().getAddressLine());
        customer.getAddress().setCity(request.getAddress().getCity());
        customer.getAddress().setState(request.getAddress().getState());
        customer.getAddress().setCountry(request.getAddress().getCountry());
        customer.getAddress().setZipCode(request.getAddress().getZipCode());
        customer.getAddress().setUpdatedAt(TimeHelper.localDateTime());
        customer.setUpdatedAt(TimeHelper.localDateTime());

        existsCustomer(customer);

        return converter.convert(customerRepository.save(customer));
    }

    public CustomerDto deleteCustomer(Integer id) {
        Customer customer = findCustomerById(id);
        customerIsActive(customer);

        customer.setIsActive(false);
        customer.getAddress().setIsActive(false);
        customer.getAddress().setDeletedAt(TimeHelper.localDateTime());
        customer.setDeletedAt(TimeHelper.localDateTime());
        customer.getAccounts().forEach(account -> {
            account.setIsActive(false);
            account.setDeletedAt(TimeHelper.localDateTime());
            account.setAccountStatus(AccountStatus.INACTIVE);
            account.setAccountLimitIsActive(false);
            account.setAccountLimitDeletedAt(TimeHelper.localDateTime());
            account.setAccountLimitStatus(AccountLimitStatus.INACTIVE);
            account.setAccountLimitDeletedAt(TimeHelper.localDateTime());
        });

        return converter.convert(customerRepository.save(customer));
    }

    public List<CustomerDto> getAllCustomers() {
        if (customerRepository.findAll().isEmpty()) {
            throw new CustomerNotFoundException(MessageHelper.CUSTOMER_NOT_FOUND);
        }
        return converter.convert(customerRepository.findAll());
    }

    public CustomerDto getCustomerById(Integer id) {
        return converter.convert(findCustomerById(id));
    }

    public CustomerDto getCustomerByNationalId(String nationalId) {
        return converter.convert(
                customerRepository.findByNationalId(nationalId)
                        .orElseThrow(
                                () -> new CustomerNotFoundException(MessageHelper.CUSTOMER_NOT_FOUND + nationalId)
                        )
        );

    }

    private void existsCustomer(Customer request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new CustomerAlreadyExceptions(
                    MessageHelper.CUSTOMER_MAIL_ALREADY_EXISTS + request.getEmail()
            );
        }
        if (customerRepository.existsByNationalId(request.getNationalId())) {
            throw new CustomerAlreadyExceptions(
                    MessageHelper.CUSTOMER_NATIONAL_ID_ALREADY_EXISTS + request.getNationalId()
            );
        }
        if (customerRepository.existsByPhone(request.getPhone())) {
            throw new CustomerAlreadyExceptions(
                    MessageHelper.CUSTOMER_PHONE_ALREADY_EXISTS + request.getPhone()
            );
        }
        if (customerRepository.existsByFirstNameIgnoreCase(request.getFirstName())) {
            throw new CustomerAlreadyExceptions(
                    MessageHelper.CUSTOMER_FIRST_NAME_ALREADY_EXISTS + request.getFirstName()
            );
        }

    }

    private void customerIsActive(Customer customer) {
        if (!customer.getIsActive()) {
            throw new CustomerNotFoundException(MessageHelper.CUSTOMER_NOT_FOUND + customer.getId());
        }
    }

    protected Customer findCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(MessageHelper.CUSTOMER_NOT_FOUND + id));
    }

}
