package com.AICE.REST_API.repository;

import com.AICE.REST_API.model.CloudVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CloudVendorRepository extends JpaRepository<CloudVendor, Long> {
//    @Query("select  cv.vendorName from CloudVendor as cv")

    @Query("select cv from CloudVendor as cv where vendorName=:name")
    List<CloudVendor> findByCloudVendorName(@Param("name") String name);
}
