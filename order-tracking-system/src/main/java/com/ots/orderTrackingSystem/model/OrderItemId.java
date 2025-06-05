package com.ots.orderTrackingSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class OrderItemId implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "order_id")
    private Long orderId;

    public OrderItemId() {
    }

    public OrderItemId(Long productId, Long orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemId that = (OrderItemId) o;
        return Objects.equals(productId, that.productId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, orderId);
    }
}
