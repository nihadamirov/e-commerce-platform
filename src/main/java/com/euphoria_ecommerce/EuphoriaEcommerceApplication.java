package com.euphoria_ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EuphoriaEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EuphoriaEcommerceApplication.class, args);
    }

}
