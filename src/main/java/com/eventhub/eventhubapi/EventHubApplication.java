package com.eventhub.eventhubapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/*
 * Main entry point of the EventHub API application.
 * This class starts the Spring Boot application.
 *
 * @SpringBootApplication enables auto-configuration,
 * component scanning, and configuration support.
 *
 * @EnableCaching enables caching features used
 * in the service layer.
 */
@SpringBootApplication
@EnableCaching
public class EventHubApplication {

    public static void main(String[] args) {

        // start the Spring Boot application
        SpringApplication.run(EventHubApplication.class, args);
    }
}