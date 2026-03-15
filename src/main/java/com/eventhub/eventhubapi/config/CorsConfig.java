package com.eventhub.eventhubapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Configuration class used to enable and customize CORS for the API.
 * CORS (Cross-Origin Resource Sharing) allows a frontend application
 * running on a different port or domain to access this backend API.
 */
@Configuration
public class CorsConfig {

    /*
     * Creates a WebMvcConfigurer bean that defines the CORS rules.
     * This allows frontend applications (like React or Vite apps)
     * running on localhost to communicate with the Spring Boot API.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        // return an implementation of WebMvcConfigurer to override CORS settings
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {

                // apply CORS rules to all API endpoints starting with /api/
                registry.addMapping("/api/**")

                        // allow requests from common frontend development servers
                        .allowedOrigins("http://localhost:3000", "http://localhost:5173")

                        // allow standard REST methods
                        .allowedMethods("GET", "POST", "PUT", "DELETE")

                        // allow any headers to be sent with the request
                        .allowedHeaders("*");
            }
        };
    }
}