package com.ots.orderTrackingSystem.service;

import com.ots.orderTrackingSystem.dto.CustomerDTO;
import com.ots.orderTrackingSystem.model.Customer;
import com.ots.orderTrackingSystem.repository.CustomerRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }
    public List<CustomerDTO> getAllCustomers() {
        return customerRepo.getCustomers();
    }
    public Customer customerGetById(Long id) {
        Optional<Customer> customerId = customerRepo.findById(id);
        return customerId.orElse(null);
    }


}
