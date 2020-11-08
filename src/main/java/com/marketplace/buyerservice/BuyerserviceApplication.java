package com.marketplace.buyerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BuyerserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuyerserviceApplication.class, args);
    }

}
