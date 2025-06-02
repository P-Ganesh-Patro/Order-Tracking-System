package com.ots.orderTrackingSystem.service;


import com.ots.orderTrackingSystem.dto.*;
import com.ots.orderTrackingSystem.exception.OrderNotFoundException;
import com.ots.orderTrackingSystem.model.*;
import com.ots.orderTrackingSystem.repository.CustomerRepo;
import com.ots.orderTrackingSystem.repository.OrderRepo;
import com.ots.orderTrackingSystem.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;


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

    public List<AllDetailsOfGivenOrderId> getAllDetailsOfAnOrderById(Long orderId) {
        return orderRepo.findOrderDetailsByOrderId(orderId);
    }

    @Transactional
    public Order createOrder(AddNewOrderByCustomerId createOrder) {
        Customer customer = customerRepo.findById(createOrder.getCustomerId())
                .orElseThrow(() -> new OrderNotFoundException("Customer not found"));

        Order newOrder = new Order();
        newOrder.setOrderDate(LocalDate.now());
        newOrder.setDeliveryDate(LocalDate.now().plusDays(5));
        newOrder.setOrderStatus(OrderStatus.NEW);
        newOrder.setCustomer(customer);

        Order savedOrder = orderRepo.save(newOrder);

        List<OrderItem> orderItems = new ArrayList<>();

        for (AddNewOrderByCustomerId.ProductQuantity pq : createOrder.getProducts()) {
            Product product = productRepo.findById(pq.getProductId())
                    .orElseThrow(() -> new OrderNotFoundException("Product Id is not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product);
            orderItem.setQuantity(pq.getQuantity());
            orderItem.setPrice((double) (product.getPrice() * pq.getQuantity()));
            orderItem.setId(new OrderItemId(product.getId(), savedOrder.getId()));

            orderItems.add(orderItem);
        }

        savedOrder.setOrderItems(orderItems);

        return orderRepo.save(savedOrder);
    }


}
