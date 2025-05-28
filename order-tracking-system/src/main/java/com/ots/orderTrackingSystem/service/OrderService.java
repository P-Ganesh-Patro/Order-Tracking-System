package com.ots.orderTrackingSystem.service;


import com.ots.orderTrackingSystem.dto.OrderDTO;
import com.ots.orderTrackingSystem.model.Order;
import com.ots.orderTrackingSystem.model.OrderItem;
import com.ots.orderTrackingSystem.model.OrderItemId;
import com.ots.orderTrackingSystem.model.Product;
import com.ots.orderTrackingSystem.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public List<OrderDTO> getAllOrdersGivenDate(String localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println(dateTimeFormatter);
        LocalDate date = LocalDate.parse(localDate, dateTimeFormatter);
        System.out.println(date);
        return orderRepo.findByOrderAfterDate(date);
    }
}
