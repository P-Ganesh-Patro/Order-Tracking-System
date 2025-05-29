package com.ots.orderTrackingSystem.controller;

import com.ots.orderTrackingSystem.dto.ListOfOrderItemsForTheGivenProduct;
import com.ots.orderTrackingSystem.dto.ListOfOrdersWithGivenStatus;
import com.ots.orderTrackingSystem.dto.OrderDTO;
import com.ots.orderTrackingSystem.dto.OrderItemGivenOrder;
import com.ots.orderTrackingSystem.model.Order;
import com.ots.orderTrackingSystem.model.OrderStatus;
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
    public ResponseEntity<?> getAllOrderCustomerById(@RequestParam Long customerId) {
        try {
            List<OrderDTO> orderList = orderService.getAllOrdersByCustomerId(customerId);
            if (orderList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer ID not found.");

            }
            return ResponseEntity.ok(orderList);
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
    public ResponseEntity<List<ListOfOrdersWithGivenStatus>> getOrderByGivenDate(@RequestParam("date") String localDate) {
        try {
            System.out.println();
            return ResponseEntity.ok(orderService.getAllOrdersGivenDate(localDate));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/orderstatus")
    public ResponseEntity<List<ListOfOrdersWithGivenStatus>> getOrderByGivenStatus(@RequestParam("orderstatus") String orderStatus) {
        try {
            System.out.println("orderStatus" + orderStatus);
            OrderStatus statusEnum = OrderStatus.valueOf(orderStatus.toUpperCase());
            return ResponseEntity.ok(orderService.getAllOrderGivenStatus(statusEnum));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/orderId")
    public ResponseEntity<List<OrderItemGivenOrder>> getOrderByGivenOrders(@RequestParam("orderId") int id) {
        try {
            return ResponseEntity.ok(orderService.getAllOrdersGivenOrder(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @GetMapping("/productName")
    public ResponseEntity<?> getOrderDetailsByProductName(@RequestParam("productName") String productName) {
        try {
            List<ListOfOrderItemsForTheGivenProduct> products = orderService.getProductDetailsByProductName(productName);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Name not Found... still not ordering " + productName);
            }

            return ResponseEntity.ok(products);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
