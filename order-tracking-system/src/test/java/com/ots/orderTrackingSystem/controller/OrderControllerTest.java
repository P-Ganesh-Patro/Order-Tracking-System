package com.ots.orderTrackingSystem.controller;

import com.ots.orderTrackingSystem.dto.*;
import com.ots.orderTrackingSystem.exception.OrderNotFoundException;
import com.ots.orderTrackingSystem.model.Order;
import com.ots.orderTrackingSystem.model.OrderStatus;
import com.ots.orderTrackingSystem.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    void testGetAllOrderCustomerById_success() {
        OrderDTO orderDTO = new OrderDTO() {
            public Long getId() {
                return 1L;
            }

            public LocalDate getOrderDate() {
                return LocalDate.now();
            }

            public LocalDate getDeliveryDate() {
                return LocalDate.now().plusDays(3);
            }

            public OrderStatus getOrderStatus() {
                return OrderStatus.NEW;
            }
        };

        Mockito.when(orderService.getAllOrdersByCustomerId(1L)).thenReturn(List.of(orderDTO));

        ResponseEntity<?> response = orderController.getAllOrderCustomerById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertFalse(((List<?>) response.getBody()).isEmpty());
    }

    @Test
    void testCreateOrders_success() {
        Order order = new Order();
        order.setId(1L);
        order.setOrderDate(LocalDate.now());

        Mockito.when(orderService.saveProduct(order)).thenReturn(order);

        ResponseEntity<Order> response = orderController.createOrders(order);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(order, response.getBody());
    }

    @Test
    void testGetOrderByGivenDate_success() {
        ListOfOrdersWithGivenStatus statusDTO = new ListOfOrdersWithGivenStatus() {
            public OrderStatus getOrderStatus() {
                return OrderStatus.DELIVERED;
            }

            @Override
            public LocalDate getDeliveryDate() {
                return null;
            }

            public Long getOrderId() {
                return 1L;
            }

            public LocalDate getOrderDate() {
                return LocalDate.of(2025, 6, 8);
            } // Implemented missing method

            @Override
            public String getCustomerName() {
                return "Harish";
            }
        };
        Mockito.when(orderService.getAllOrdersGivenDate("2025-06-08")).thenReturn(List.of(statusDTO));
        ResponseEntity<List<ListOfOrdersWithGivenStatus>> response =
                orderController.getOrderByGivenDate("2025-06-08");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(OrderStatus.DELIVERED, response.getBody().get(0).getOrderStatus());
        assertEquals(LocalDate.of(2025, 6, 8), response.getBody().get(0).getOrderDate());
    }


    @Test
    void testGetOrderByGivenStatus_success() {
        ListOfOrdersWithGivenStatus statusDTO = new ListOfOrdersWithGivenStatus() {
            public Long getOrderId() {
                return 1L;
            }

            public OrderStatus getOrderStatus() {
                return OrderStatus.DELIVERED;
            }

            @Override
            public LocalDate getDeliveryDate() {
                return null;
            }

            public LocalDate getOrderDate() {
                return LocalDate.now();
            }

            @Override
            public String getCustomerName() {
                return "john";
            }
        };

        Mockito.when(orderService.getAllOrderGivenStatus(OrderStatus.DELIVERED)).thenReturn(List.of(statusDTO));

        ResponseEntity<List<ListOfOrdersWithGivenStatus>> response =
                orderController.getOrderByGivenStatus("DELIVERED");

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testGetOrderDetailsByProductName_success() {
        ListOfOrderItemsForTheGivenProduct product =
                new ListOfOrderItemsForTheGivenProduct(
                        LocalDate.of(2025, 6, 8),
                        799.99,
                        2,
                        "Ganesh",
                        "mouse"
                );

        Mockito.when(orderService.getProductDetailsByProductName("mouse"))
                .thenReturn(List.of(product));

        ResponseEntity<?> response = orderController.getOrderDetailsByProductName("mouse");

        assertEquals(200, response.getStatusCodeValue());

        List<ListOfOrderItemsForTheGivenProduct> body =
                (List<ListOfOrderItemsForTheGivenProduct>) response.getBody();

        assertNotNull(body);
        assertEquals(1, body.size());
        assertEquals("mouse", body.get(0).getProductName());
        assertEquals(2, body.get(0).getQuantity());
    }

    @Test
    void testGetOrderDetailsByProductName_notFound() {
        Mockito.when(orderService.getProductDetailsByProductName("Phone")).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(OrderNotFoundException.class, () ->
                orderController.getOrderDetailsByProductName("Phone"));

        assertEquals("Order not found", exception.getMessage());
    }

    @Test
    void testGetOrderDetailsById_success() {
        AllDetailsOfGivenOrderId orderDetail = new AllDetailsOfGivenOrderId() {
            @Override
            public Long getOrderId() {
                return 1L;
            }


            @Override
            public Long getCustomerId() {
                return 1L;
            }

            @Override
            public String getCustomerName() {
                return "chandu";
            }

            @Override
            public String getCustomerEmail() {
                return "chandu@gmail.com";
            }
            @Override
            public String getCustomerMobile() {
                return "8763514998";
            }

            @Override
            public int getProductId() {
                return 1;
            }

            public String getProductName() {
                return "Shoes";
            }

            @Override
            public double getPrice() {
                return 123;
            }

            @Override
            public int getQuantityOrdered() {
                return 2;
            }

            @Override
            public LocalDate getOrderDate() {
                return null;
            }

            @Override
            public LocalDate getDeliveryDate() {
                return null;
            }

            public Integer getQuantity() {
                return 1;
            }
        };

        Mockito.when(orderService.getAllDetailsOfAnOrderById(1L)).thenReturn(List.of(orderDetail));

        ResponseEntity<?> response = orderController.getOrderDetailsById(1L);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testGetOrderDetailsById_notFound() {
        Mockito.when(orderService.getAllDetailsOfAnOrderById(100L)).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(OrderNotFoundException.class, () ->
                orderController.getOrderDetailsById(100L));

        assertTrue(exception.getMessage().contains("Order not found for the given orderId"));
    }

    @Test
    void testCreateOrder_success() {
        AddNewOrderByCustomerId orderRequest = new AddNewOrderByCustomerId();
        Order newOrder = new Order();
        newOrder.setId(100L);

        Mockito.when(orderService.createOrder(orderRequest)).thenReturn(newOrder);

        Order response = orderController.createOrder(orderRequest);
        assertEquals(100L, response.getId());
    }
}
