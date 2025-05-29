package com.ots.orderTrackingSystem.dto;

import java.time.LocalDate;

public class ListOfOrderItemsForTheGivenProduct {
    private LocalDate orderDate;
    private double price;
    private int quantity;
    private String customerName;
    private String productName;

    public ListOfOrderItemsForTheGivenProduct(LocalDate orderDate, double price, int quantity, String customerName, String productName) {
        this.orderDate = orderDate;
        this.price = price;
        this.quantity = quantity;
        this.customerName = customerName;
        this.productName = productName;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
