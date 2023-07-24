package com.rduddilla.sandbox.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FiltersApp implements CommandLineRunner {

    @Autowired
    public FiltersApp() {
    }
    public static void main(String[] args) {
        SpringApplication.run(FiltersApp.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
    }
}
