package com.AICE.REST_API.service.impelmention;

import com.AICE.REST_API.exception.CloudVendorNotFoundException;
import com.AICE.REST_API.model.CloudVendor;
import com.AICE.REST_API.repository.CloudVendorRepository;
import com.AICE.REST_API.service.CloudVendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudVendorServiceImpl implements CloudVendorService {

    CloudVendorRepository cloudVendorRepository;

    public CloudVendorServiceImpl(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }


    @Override
    public CloudVendor createCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return cloudVendor;
    }

    @Override
    public CloudVendor updateCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return cloudVendor;
    }

    @Override
    public CloudVendor getCloudVendor(Long CloudVendorId) {
        if (cloudVendorRepository.findById(CloudVendorId).isEmpty()) {
            throw new CloudVendorNotFoundException("cloudVendor Id is not found..");
        }
        return cloudVendorRepository.findById(CloudVendorId).get();
    }


    @Override
    public List<CloudVendor> getAllCloudVendors() {
        return cloudVendorRepository.findAll();

    }

    @Override
    public String deleteCloudVendors(Long cloudVendorId) {
        CloudVendor cloudVendor = cloudVendorRepository.findById(cloudVendorId).orElseThrow(() -> new RuntimeException("CloudVendorId is not found " + cloudVendorId));
        cloudVendorRepository.deleteById(cloudVendorId);
        return cloudVendor.getVendorName() + " deleted.. and id:- " + cloudVendorId;
    }
}
