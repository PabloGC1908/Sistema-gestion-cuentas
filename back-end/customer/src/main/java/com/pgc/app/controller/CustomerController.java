package com.pgc.app.controller;

import com.pgc.app.dto.request.CustomerRequest;
import com.pgc.app.exception.classes.ResourceNotFoundException;
import com.pgc.app.model.Customer;
import com.pgc.app.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomer() {
        List<Customer> customers = customerService.getCustomers();

        if (customers.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer customer = customerService.getCustomer(id);

        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<String> postCustomer(@RequestBody CustomerRequest customerRequest) throws ResourceNotFoundException {
        try {
            String response = customerService.registerCustomer(customerRequest);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/list")
    public ResponseEntity<String> postCustomers(@RequestBody List<CustomerRequest> customerRequestList) throws ResourceNotFoundException {
        try {
            String response = customerService.registerCustomers(customerRequestList);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
