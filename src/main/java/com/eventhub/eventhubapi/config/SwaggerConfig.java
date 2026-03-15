package com.eventhub.eventhubapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Swagger configuration for the EventHub API.
 * Swagger (OpenAPI) is used to automatically generate
 * API documentation and allow testing endpoints in the browser.
 */
@Configuration
public class SwaggerConfig {

    /*
     * Creates the OpenAPI configuration used by Swagger.
     * This sets basic API information such as title,
     * description, version, and contact info.
     */
    @Bean
    public OpenAPI eventHubOpenAPI() {

        // build the OpenAPI documentation object
        return new OpenAPI()
                .info(new Info()
                        .title("EventHub API") // name shown in Swagger UI
                        .description("Assignment 1 API for EventHub event management platform")
                        .version("1.0") // API version
                        .contact(new Contact()
                                .name("J") // API author
                                .email("student@example.com"))); // contact email
    }
}