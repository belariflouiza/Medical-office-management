package com.example.appoitmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication

public class AppoitmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppoitmentServiceApplication.class, args);
    }

}
