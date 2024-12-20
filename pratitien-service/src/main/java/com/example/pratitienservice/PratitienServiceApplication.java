package com.example.pratitienservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PratitienServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PratitienServiceApplication.class, args);
    }

}
