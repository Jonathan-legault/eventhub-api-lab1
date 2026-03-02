package com.eventhub.eventhubapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * HealthController
 *
 * Provides a basic health check endpoint for the application.
 * Used to verify that the API is running correctly.
 *
 * Endpoint: GET /api/health
 *
 * Returns application status, name, active profile, and version.
 */
@RestController
public class HealthController {

    /**
     * Application version loaded from application properties.
     * Defaults to "1.0" if not defined.
     */
    @Value("${app.version:1.0}")
    private String version;

    /**
     * Active Spring profile (dev, test, prod).
     * Defaults to "default" if none is set.
     */
    @Value("${spring.profiles.active:default}")
    private String profile;

    /**
     * Application name defined in application properties.
     */
    @Value("${spring.application.name:eventhub-api}")
    private String appName;

    /**
     * Health check endpoint.
     *
     * @return a map containing application status information
     */
    @GetMapping("/api/health")
    public Map<String, String> health() {
        return Map.of(
                "status", "UP",
                "app", appName,
                "profile", profile,
                "version", version
        );
    }
}