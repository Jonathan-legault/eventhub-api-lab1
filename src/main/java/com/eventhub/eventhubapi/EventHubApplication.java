package com.eventhub.eventhubapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * EventHubApplication
 *
 * Main entry point for the EventHub Spring Boot application.
 *
 * The @SpringBootApplication annotation enables:
 * - Component scanning
 * - Auto-configuration
 * - Configuration support
 *
 * This class bootstraps and launches the embedded server.
 */
@SpringBootApplication
public class EventHubApplication {

    /**
     * Main method used to launch the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(EventHubApplication.class, args);
    }

}