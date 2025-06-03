package com.ots.orderTrackingSystem.dto;

import java.time.LocalDate;

public interface AllDetailsOfGivenOrderId {

    Long getOrderId();
    Long getCustomerId();
    String getCustomerName();
    String getCustomerEmail();
    String getCustomerMobile();

    int getProductId();
    String getProductName();
    double getPrice();
    int getQuantityOrdered();
    LocalDate getOrderDate();
    LocalDate getDeliveryDate();
}
