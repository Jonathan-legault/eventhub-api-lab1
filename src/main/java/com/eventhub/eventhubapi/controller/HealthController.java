package com.eventhub.eventhubapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/*
 * Simple health check controller.
 * This endpoint can be used to verify that the API is running.
 * It returns basic information about the application.
 */
@RestController
public class HealthController {

    // application version from application.properties
    // defaults to 1.0 if not specified
    @Value("${app.version:1.0}")
    private String version;

    // active Spring profile (dev, test, prod)
    // defaults to "default" if none is set
    @Value("${spring.profiles.active:default}")
    private String profile;

    // application name defined in application properties
    @Value("${spring.application.name:eventhub-api}")
    private String appName;

    /*
     * GET /api/health
     * Returns basic status information about the API.
     */
    @GetMapping("/api/health")
    public Map<String, String> health() {

        // return simple status information as a JSON response
        return Map.of(
                "status", "UP",
                "app", appName,
                "profile", profile,
                "version", version
        );
    }
}