package com.ots.orderTrackingSystem.dto;

import com.ots.orderTrackingSystem.model.OrderStatus;

import java.time.LocalDate;

public interface OrderDTO {
    Long getId();

    Long getCid();

    LocalDate getOrderDate();

    LocalDate getDeliveryDate();

    OrderStatus getOrderStatus();
}
