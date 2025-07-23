package com.example.validateform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ValidateFormApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValidateFormApplication.class, args);
    }

}
