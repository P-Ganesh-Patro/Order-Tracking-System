package com.ots.orderTrackingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class OrderTrackingSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderTrackingSystemApplication.class, args);
	}
}
