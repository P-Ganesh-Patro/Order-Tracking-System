package com.AICE.REST_API.respository;

import com.AICE.REST_API.model.CloudVendor;
import com.AICE.REST_API.repository.CloudVendorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class CloudVendorRepositoryTest {
    CloudVendor cloudVendor;
    CloudVendor cloudVendor2;

    @Autowired
    private CloudVendorRepository cloudVendorRepository;


    @BeforeEach
    void initialize() {
        cloudVendor = new CloudVendor(101L, "Amazon", "USA", "6301900547");
        cloudVendorRepository.save(cloudVendor);
        cloudVendor2 = new CloudVendor(102L, "flipkart", "india", "6301900547");
        cloudVendorRepository.save(cloudVendor2);
    }

    @Test
    void testFindByVendorName() {
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findByCloudVendorName("Amazon");
        Assertions.assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
        Assertions.assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
    }

    @Test
    void testFindByVendorAddress() {
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findByCloudVendorName("flipkart");
        Assertions.assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(cloudVendor2.getVendorAddress());
    }

    //    failed test case
    @Test
    void testFindByVendor_NotFound() {
        List<CloudVendor> cloudVendors = cloudVendorRepository.findByCloudVendorName("gcp");
        Assertions.assertThat(cloudVendors.isEmpty()).isTrue();
    }


    @AfterEach
    void destroy() {
        cloudVendor = null;
        cloudVendorRepository.deleteAll();
    }


}
