package com.ots.orderTrackingSystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class AddNewOrderByCustomerId {

    @NotNull(message = "Customer Id is required")
    private Long customerId;

    @NotEmpty(message = "Product List cannot be empty")
    private List<ProductQuantity> products;

    @Data
    public static class ProductQuantity {

        @NotNull(message = "Product Id is required")
        private Long ProductId;

        @Min(value = 1, message = "Quantity must be at least 1")
        private int quantity;
    }

}
