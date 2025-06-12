package com.ots.orderTrackingSystem.controller;

import com.ots.orderTrackingSystem.dto.ProductDTO;
import com.ots.orderTrackingSystem.exception.OrderNotFoundException;
import com.ots.orderTrackingSystem.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProductsByPagination(@RequestParam int page, @RequestParam int size) {
        try {
            List<ProductDTO> allProducts = productService.getAllProductsByPagination(page, size);
            return ResponseEntity.ok(allProducts);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> getProductsByName(@RequestParam String name) {
        try {
            List<ProductDTO> productDTOS = productService.getAllProductsByName(name);
            if (productDTOS.isEmpty()) {
                throw new OrderNotFoundException("Product Not found:-  " + name);
            }
            return ResponseEntity.ok(productDTOS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
