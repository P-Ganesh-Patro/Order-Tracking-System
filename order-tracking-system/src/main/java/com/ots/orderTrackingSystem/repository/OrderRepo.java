package com.ots.orderTrackingSystem.repository;

import com.ots.orderTrackingSystem.dto.*;
import com.ots.orderTrackingSystem.model.Order;
import com.ots.orderTrackingSystem.model.OrderStatus;
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

    @Query("SELECT o.id AS orderId, o.orderDate AS orderDate, c.name AS customerName, o.orderStatus AS orderStatus, o.deliveryDate AS deliveryDate FROM Order o JOIN o.customer c WHERE o.deliveryDate > :deliveryDate")
    List<ListOfOrdersWithGivenStatus> findByOrderAfterDate(@Param("deliveryDate") LocalDate date);

    @Query("SELECT o.id AS orderId, o.orderDate AS orderDate, c.name AS customerName, o.orderStatus AS orderStatus, o.deliveryDate AS deliveryDate FROM Order o JOIN o.customer c WHERE o.orderStatus = :orderStatus")
    List<ListOfOrdersWithGivenStatus> findByOrderGivenStatus(@Param("orderStatus") OrderStatus orderStatus);

    @Query("SELECT p.name AS productName, oi.quantity AS quantity, oi.price AS price FROM OrderItem oi JOIN oi.product p JOIN oi.order o WHERE o.id = :orderId")
    List<OrderItemGivenOrder> findByOrdersGivenOrder(@Param("orderId") int orderId);

    @Query("""
                SELECT new com.ots.orderTrackingSystem.dto.ListOfOrderItemsForTheGivenProduct(
                    o.orderDate, oi.price, oi.quantity, c.name, p.name
                )
                FROM Order o
                JOIN o.orderItems oi
                JOIN o.customer c
                JOIN oi.product p
                WHERE p.name LIKE CONCAT('%', :productName, '%')
            """)
    List<ListOfOrderItemsForTheGivenProduct> findOrderByGivenProductName(@Param("productName") String productName);

    @Query("""
            SELECT 
                o.id AS orderId,
                c.id AS customerId,
                c.name AS customerName,
                c.email AS customerEmail,
                c.mobileNumber AS customerMobile,
                p.id AS productId,
                p.name AS productName,
                p.price AS price,
                oi.quantity AS quantityOrdered,
                o.orderDate AS orderDate,
                o.deliveryDate AS deliveryDate
            FROM Order o
            JOIN o.customer c
            JOIN o.orderItems oi
            JOIN oi.product p
            WHERE o.id = :orderId
            """)
    List<AllDetailsOfGivenOrderId> findOrderDetailsByOrderId(@Param("orderId") Long orderId);

}
