package com.ots.orderTrackingSystem.dto;


import com.ots.orderTrackingSystem.model.OrderStatus;

import java.time.LocalDate;

public interface ListOfOrdersWithGivenStatus {
    Long getOrderId();
    LocalDate getOrderDate();
    String getCustomerName();
    OrderStatus getOrderStatus();
    LocalDate getDeliveryDate();
}
