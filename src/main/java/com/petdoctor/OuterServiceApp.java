package com.petdoctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OuterServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(OuterServiceApp.class, args);
    }
}
