package com.ots.orderTrackingSystem.controller;

import com.ots.orderTrackingSystem.dto.CustomerDTO;
import com.ots.orderTrackingSystem.model.Customer;
import com.ots.orderTrackingSystem.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void testGetAllCustomers() {
        CustomerDTO customer = new CustomerDTO() {
            public Long getId() {
                return 1L;
            }

            public String getName() {
                return "Ganesh";
            }

            public String getEmail() {
                return "ganesh@example.com";
            }

            public String getMobileNumber() {
                return "9999999999";
            }
        };
        Mockito.when(customerService.getAllCustomers()).thenReturn(List.of(customer));
        ResponseEntity<List<CustomerDTO>> response = customerController.getAllCustomers();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Ganesh", response.getBody().get(0).getName());
    }

    @Test
    void testGetCustomerById() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Ganesh");
        Mockito.when(customerService.customerGetById(1L)).thenReturn(customer);
        ResponseEntity<?> response = customerController.createOrderByCustomerId(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof Customer);
        assertEquals("Ganesh", ((Customer) response.getBody()).getName());
    }


}
