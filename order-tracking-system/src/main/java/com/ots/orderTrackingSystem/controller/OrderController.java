package com.ots.orderTrackingSystem.controller;

import com.ots.orderTrackingSystem.dto.OrderDTO;
import com.ots.orderTrackingSystem.model.Order;
import com.ots.orderTrackingSystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/customerId")
    public ResponseEntity<List<OrderDTO>> getAllOrderCustomerById(@RequestParam Long customerId) {
        try {
            return ResponseEntity.ok(orderService.getAllOrdersByCustomerId(customerId));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Order> createOrders(@RequestBody Order order) {
        try {
            Order orders = orderService.saveProduct(order);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/date")
    public ResponseEntity<List<OrderDTO>> getOrderByGivenDate(@RequestParam("date") String localDate) {
        try {
            return ResponseEntity.ok(orderService.getAllOrdersGivenDate(localDate));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
