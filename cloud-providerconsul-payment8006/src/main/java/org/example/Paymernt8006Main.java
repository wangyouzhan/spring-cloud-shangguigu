package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Paymernt8006Main {

    public static void main(String[] args) {
        SpringApplication.run(Paymernt8006Main.class, args);
    }
}
