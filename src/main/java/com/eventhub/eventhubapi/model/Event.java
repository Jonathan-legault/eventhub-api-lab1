package com.eventhub.eventhubapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Event Entity Class
 *
 * Represents an event within the EventHub platform.
 * This model is used internally for business logic and
 * in-memory storage.
 *
 * JSON serialization rules:
 * - Null fields are excluded from responses.
 * - Date fields are formatted using ISO-8601 standard.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {

    // ========================
    // Fields
    // ========================

    private Long id;
    private String name;
    private String description;
    private BigDecimal ticketPrice;
    private String category;
    private Boolean active;

    /**
     * Event date formatted as ISO-8601:
     * yyyy-MM-dd'T'HH:mm:ss
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime eventDate;

    /**
     * Timestamp when event was created.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    /**
     * Timestamp when event was last updated.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    // ========================
    // Constructors
    // ========================

    /**
     * Default constructor required for JSON deserialization.
     */
    public Event() {}

    /**
     * Full constructor used internally for entity creation.
     */
    public Event(Long id,
                 String name,
                 String description,
                 BigDecimal ticketPrice,
                 String category,
                 Boolean active,
                 LocalDateTime eventDate,
                 LocalDateTime createdAt,
                 LocalDateTime updatedAt) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.ticketPrice = ticketPrice;
        this.category = category;
        this.active = active;
        this.eventDate = eventDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ========================
    // Getters and Setters
    // ========================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(BigDecimal ticketPrice) { this.ticketPrice = ticketPrice; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public LocalDateTime getEventDate() { return eventDate; }
    public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}