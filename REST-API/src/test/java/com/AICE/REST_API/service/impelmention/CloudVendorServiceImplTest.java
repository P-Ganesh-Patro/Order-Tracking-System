package com.AICE.REST_API.service.impelmention;

import com.AICE.REST_API.model.CloudVendor;
import com.AICE.REST_API.repository.CloudVendorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

class CloudVendorServiceImplTest {
    @Mock
    private CloudVendorRepository cloudVendorRepository;

    private CloudVendorServiceImpl cloudVendorService;
    AutoCloseable autoCloseable;
    CloudVendor cloudVendor;

    @BeforeEach
    void initialize() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepository);
        cloudVendor = new CloudVendor(120L, "Amazon", "USA", "6301900654");
    }

    @AfterEach
    void destroy() {
    }

    @Test
    void createCloudVendor() {
    }
    
    @Test
    void updateCloudVendor() {
    }

    @Test
    void getCloudVendor() {
    }

    @Test
    void getAllCloudVendors() {
    }

    @Test
    void deleteCloudVendors() {
    }
}