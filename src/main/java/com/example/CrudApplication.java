package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {

        SpringApplication.run(CrudApplication.class, args);

        System.out.println("It is running");
    }

}
