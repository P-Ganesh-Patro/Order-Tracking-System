package com.ots.orderTrackingSystem.service;


import com.ots.orderTrackingSystem.dto.ListOfOrderItemsForTheGivenProduct;
import com.ots.orderTrackingSystem.dto.ListOfOrdersWithGivenStatus;
import com.ots.orderTrackingSystem.dto.OrderDTO;
import com.ots.orderTrackingSystem.dto.OrderItemGivenOrder;
import com.ots.orderTrackingSystem.model.*;
import com.ots.orderTrackingSystem.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;

    public List<OrderDTO> getAllOrdersByCustomerId(Long id) {

        return orderRepo.getOrdersByCustomerId(id);


    }

    public Order saveProduct(Order order) {

        return orderRepo.save(order);
    }

    public List<ListOfOrdersWithGivenStatus> getAllOrdersGivenDate(String localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println(dateTimeFormatter);
        LocalDate date = LocalDate.parse(localDate, dateTimeFormatter);
        System.out.println(date);
        return orderRepo.findByOrderAfterDate(date);
    }

    public List<ListOfOrdersWithGivenStatus> getAllOrderGivenStatus(OrderStatus orderStatus) {
        System.out.println("orderStatus :- " + orderStatus);
        return orderRepo.findByOrderGivenStatus(orderStatus);
    }

    public List<OrderItemGivenOrder> getAllOrdersGivenOrder(int orderId) {
        return orderRepo.findByOrdersGivenOrder(orderId);
    }

    public List<ListOfOrderItemsForTheGivenProduct> getProductDetailsByProductName(String name) {
        return orderRepo.findOrderByGivenProductName(name);
    }
}
