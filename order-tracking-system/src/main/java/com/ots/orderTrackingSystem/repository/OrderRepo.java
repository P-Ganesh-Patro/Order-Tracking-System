package com.ots.orderTrackingSystem.repository;

import com.ots.orderTrackingSystem.dto.OrderDTO;
import com.ots.orderTrackingSystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    @Query("SELECT o.id AS id, o.orderDate AS orderDate, o.deliveryDate AS deliveryDate, o.orderStatus AS orderStatus FROM Order o WHERE o.customer.id = :customerId")
    List<OrderDTO> getOrdersByCustomerId(@Param("customerId") Long id);

    @Query("SELECT o.id AS id, o.orderDate AS orderDate, o.deliveryDate AS deliveryDate, o.orderStatus AS orderStatus FROM Order o WHERE o.orderDate > :date")
    List<OrderDTO> findByOrderAfterDate(@Param("date") LocalDate date);
}
