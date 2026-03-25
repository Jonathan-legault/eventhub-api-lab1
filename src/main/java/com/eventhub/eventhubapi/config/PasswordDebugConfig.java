package com.eventhub.eventhubapi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordDebugConfig {

    @Bean
    public CommandLineRunner printEncodedPassword(PasswordEncoder passwordEncoder) {
        return args -> {
            String rawPassword = "password123";
            String encodedPassword = passwordEncoder.encode(rawPassword);

            System.out.println("=====================================");
            System.out.println("BCrypt for password123:");
            System.out.println(encodedPassword);
            System.out.println("=====================================");

        };
    }

    @Bean
    public CommandLineRunner printEncodedPassword1(PasswordEncoder passwordEncoder) {
        return args -> {
            String rawPassword = "admin123";
            String encodedPassword = passwordEncoder.encode(rawPassword);

            System.out.println("=====================================");
            System.out.println("BCrypt for admin123:");
            System.out.println(encodedPassword);
            System.out.println("=====================================");

        };
    }
}