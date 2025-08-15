package com.example.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PdtsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PdtsApplication.class, args);
    }
}
