package com.AICE.REST_API.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cloud_vendor_info")
public class CloudVendor {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendorId")
    private Long vendorId;
    @Column(name = "vendorName")
    private String vendorName;
    @Column(name = "vendorAddress")
    private String vendorAddress;
    @Column(name = "vendorPhoneNumber")
    private String vendorPhoneNumber;

    public CloudVendor(Long vendorId, String vendorName, String vendorAddress, String vendorPhoneNumber) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

    public CloudVendor() {
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getVendorPhoneNumber() {
        return vendorPhoneNumber;
    }

    public void setVendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }
}
