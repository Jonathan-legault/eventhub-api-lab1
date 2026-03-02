package com.eventhub.eventhubapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @Value("${app.version:1.0}")
    private String version;

    @Value("${spring.profiles.active:default}")
    private String profile;

    @Value("${spring.application.name:eventhub-api}")
    private String appName;

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