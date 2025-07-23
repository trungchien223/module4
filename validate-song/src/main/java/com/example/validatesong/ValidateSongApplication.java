package com.example.validatesong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ValidateSongApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValidateSongApplication.class, args);
    }

}
