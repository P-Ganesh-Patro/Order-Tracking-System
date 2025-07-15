package com.AICE.REST_API.service;

import com.AICE.REST_API.model.CloudVendor;

import java.util.List;

public interface CloudVendorService {
    public CloudVendor createCloudVendor(CloudVendor cloudVendor);

    public CloudVendor updateCloudVendor(CloudVendor cloudVendor);

    public CloudVendor getCloudVendor(Long CloudVendorId);

    public List<CloudVendor> getAllCloudVendors();

    public String deleteCloudVendors(Long cloudVendorId);

}
