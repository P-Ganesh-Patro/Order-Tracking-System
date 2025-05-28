package com.ots.orderTrackingSystem.dto;

import com.ots.orderTrackingSystem.model.OrderStatus;

import java.time.LocalDate;

public interface OrderDTO {
    Long getId();

    LocalDate getOrderDate();

    LocalDate getDeliveryDate();

    OrderStatus getOrderStatus();
}
