package com.ots.orderTrackingSystem.service;

import com.ots.orderTrackingSystem.dto.ProductDTO;
import com.ots.orderTrackingSystem.model.Product;
import com.ots.orderTrackingSystem.repository.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<ProductDTO> getAllProductsByPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepo.getAllProducts(pageable).getContent();
    }


}
