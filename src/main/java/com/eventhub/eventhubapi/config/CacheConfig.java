package com.eventhub.eventhubapi.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/*
 * Cache configuration for the EventHub API.
 * This class sets up Caffeine caching so frequently requested data
 * (like events and categories) can be stored in memory instead of
 * always querying the database.
 */
@Configuration
public class CacheConfig {

    /*
     * Creates the Caffeine cache configuration.
     * Entries expire after 10 minutes and the cache
     * can store up to 100 items.
     */
    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES) // remove items 10 minutes after they are written
                .maximumSize(100); // limit cache size
    }

    /*
     * Creates the CacheManager used by Spring.
     * Defines two caches used in the application:
     * "events" and "categories".
     */
    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {

        // create cache manager with named caches
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("events", "categories");

        // apply the caffeine settings defined above
        cacheManager.setCaffeine(caffeine);

        return cacheManager;
    }
}