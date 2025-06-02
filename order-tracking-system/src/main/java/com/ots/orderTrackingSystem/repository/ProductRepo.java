package com.ots.orderTrackingSystem.repository;

import com.ots.orderTrackingSystem.dto.ProductDTO;
import com.ots.orderTrackingSystem.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;


@Repository
public interface ProductRepo extends ListPagingAndSortingRepository<Product, Long>, JpaRepository<Product, Long> {

    @Query("select p.id as id, p.name as name, p.description as description, p.price as price from Product p")
    Page<ProductDTO> getAllProducts(Pageable pageable);

    List<ProductDTO> findByNameContainingIgnoreCase(String Name);

}
