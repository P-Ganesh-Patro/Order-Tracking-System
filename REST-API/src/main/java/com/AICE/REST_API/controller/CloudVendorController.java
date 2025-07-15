package com.AICE.REST_API.controller;


import com.AICE.REST_API.model.CloudVendor;
import com.AICE.REST_API.response.ResponseHandler;
import com.AICE.REST_API.service.impelmention.CloudVendorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin
@RestController
@RequestMapping("/api")
public class CloudVendorController {
    CloudVendorServiceImpl cloudVendorService;

    public CloudVendorController(CloudVendorServiceImpl cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCloudVendorById(@PathVariable("id") Long id) {
        CloudVendor cloudVendor = cloudVendorService.getCloudVendor(id);
        return ResponseHandler.responseBuilder("Dear " + cloudVendor.getVendorName() + " GivenId Details Fetching successfully.", HttpStatus.OK, cloudVendorService.getCloudVendor(id));
    }

    @GetMapping()
    public ResponseEntity<Object> getCloudVendorById() {
        return ResponseHandler.responseBuilder("All cloud Vendor details fetched successfully", HttpStatus.OK, cloudVendorService.getAllCloudVendors());

    }

    @PostMapping()
    public ResponseEntity<Object> createCloudVendor(@RequestBody CloudVendor cloudVendor) {
        return ResponseHandler.responseBuilder("Given Data Saved Successfully..", HttpStatus.CREATED, cloudVendorService.createCloudVendor(cloudVendor));
    }

    @PutMapping()
    public ResponseEntity<Object> updateCloudVendorById(@RequestBody CloudVendor cloudVendor) {
        return ResponseHandler.responseBuilder("Cloud vendor updated successfully..", HttpStatus.OK, cloudVendorService.updateCloudVendor(cloudVendor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCloudVendorById(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Cloud vendor deleted successfully..", HttpStatus.OK, cloudVendorService.deleteCloudVendors(id));

    }

}
