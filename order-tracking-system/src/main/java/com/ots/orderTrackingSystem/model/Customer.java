package com.ots.orderTrackingSystem.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

//    public void addOrder(Order order) {
//        if (!orders.contains(order)) {
//            order.setCustomer(this);
//        }
//    }
//
//    public void removeOrder(Order order) {
//        if (orders.remove(order)) {
//            order.setCustomer(null);
//        }
//    }


}
