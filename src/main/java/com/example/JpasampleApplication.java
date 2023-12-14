package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class JpasampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpasampleApplication.class, args);
    }

}
