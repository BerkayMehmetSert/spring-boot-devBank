package com.bms.devbank.controller;

import com.bms.devbank.dto.CustomerDto;
import com.bms.devbank.dto.request.CreateCustomerRequest;
import com.bms.devbank.dto.request.UpdateCustomerRequest;
import com.bms.devbank.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/customer")
@Tag(name = "Customer", description = "Customer API")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Create customer",
            description = "Create customer",
            tags = {"Customer"})
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @Operation(summary = "Update customer",
            description = "Update customer for customer id",
            tags = {"Customer"})
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Integer id,
                                                      @Valid @RequestBody UpdateCustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(id, request));
    }

    @Operation(summary = "Delete customer",
            description = "Delete customer for customer id",
            tags = {"Customer"})
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDto> deleteCustomerById(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }

    @Operation(summary = "Get customer by id",
            description = "Get customer by id",
            tags = {"Customer"})
    @GetMapping("/getCustomerById/{id}")
    public CustomerDto getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    @Operation(summary = "Get customer by national id",
            description = "Get customer by national id",
            tags = {"Customer"})
    @GetMapping("/{nationalId}")
    public CustomerDto getCustomerByNationalId(@PathVariable String nationalId) {
        return customerService.getCustomerByNationalId(nationalId);
    }

    @Operation(summary = "Get all customer",
            description = "Get all customer",
            tags = {"Customer"})
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

}
