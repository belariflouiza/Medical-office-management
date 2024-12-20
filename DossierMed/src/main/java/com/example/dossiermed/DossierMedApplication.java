package com.example.dossiermed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DossierMedApplication {

    public static void main(String[] args) {
        SpringApplication.run(DossierMedApplication.class, args);
    }

}
