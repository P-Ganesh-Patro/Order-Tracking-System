package com.ots.orderTrackingSystem.repository;

import com.ots.orderTrackingSystem.dto.CustomerDTO;
import com.ots.orderTrackingSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query("select c.id as id, c.name as name, c.email as email, c.mobileNumber as mobileNumber from Customer c")
     List<CustomerDTO> getCustomers();
 


}
