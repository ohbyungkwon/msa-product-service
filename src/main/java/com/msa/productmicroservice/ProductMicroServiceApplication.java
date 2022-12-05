package com.msa.productmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProductMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductMicroServiceApplication.class, args);
    }

}
