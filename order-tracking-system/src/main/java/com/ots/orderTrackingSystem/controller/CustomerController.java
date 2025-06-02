package com.ots.orderTrackingSystem.controller;

import com.ots.orderTrackingSystem.dto.CustomerDTO;
import com.ots.orderTrackingSystem.exception.OrderNotFoundException;
import com.ots.orderTrackingSystem.model.Customer;
import com.ots.orderTrackingSystem.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        try {
            List<CustomerDTO> customers = customerService.getAllCustomers();
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//
    @GetMapping("/{id}")
    public ResponseEntity<?> createOrderByCustomerId(@PathVariable  Long id) {
        Customer customer = customerService.customerGetById(id);
        if (customer == null) {
            throw new OrderNotFoundException("Customer Id is not found");
        }
        return ResponseEntity.ok(customer);
    }




}
