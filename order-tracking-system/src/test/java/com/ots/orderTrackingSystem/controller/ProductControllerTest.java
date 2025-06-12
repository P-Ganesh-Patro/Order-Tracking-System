package com.ots.orderTrackingSystem.controller;
import com.ots.orderTrackingSystem.dto.ProductDTO;
import com.ots.orderTrackingSystem.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void testGetAllProductsByPagination_success() {
        ProductDTO product = new ProductDTO() {
            public Long getId() { return 1L; }
            public String getName() { return "Laptop"; }
            public String getDescription() { return "Dell XPS"; }
            public String getPrice() { return "999.99"; }
        };

        Mockito.when(productService.getAllProductsByPagination(0, 5))
                .thenReturn(List.of(product));

        ResponseEntity<List<ProductDTO>> response = productController.getAllProductsByPagination(0, 5);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Laptop", response.getBody().get(0).getName());
    }

    @Test
    void testGetProductsByName_success() {
        ProductDTO product = new ProductDTO() {
            public Long getId() { return 1L; }
            public String getName() { return "Phone"; }
            public String getDescription() { return "Smartphone"; }
            public String getPrice() { return "499.99"; }
        };

        Mockito.when(productService.getAllProductsByName("Phone"))
                .thenReturn(List.of(product));

        ResponseEntity<?> response = productController.getProductsByName("Phone");

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());

        List<ProductDTO> productList = (List<ProductDTO>) response.getBody();
        assertEquals(1, productList.size());
        assertEquals("Phone", productList.get(0).getName());
    }

    @Test
    void testGetProductsByName_notFound() {
        Mockito.when(productService.getAllProductsByName("UnknownProduct"))
                .thenReturn(List.of());

        ResponseEntity<?> response = productController.getProductsByName("mouse");
        assertEquals(500, response.getStatusCodeValue());
    }
}
