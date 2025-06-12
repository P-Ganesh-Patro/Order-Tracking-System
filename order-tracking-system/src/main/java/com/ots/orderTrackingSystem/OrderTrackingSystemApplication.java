package com.ots.orderTrackingSystem;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class OrderTrackingSystemApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OrderTrackingSystemApplication.class);

//        SpringApplication.run(OrderTrackingSystemApplication.class, args);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);


    }
}
