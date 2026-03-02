package com.eventhub.eventhubapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * EventDTO (Data Transfer Object)
 *
 * Used to transfer event data between the API layer
 * and client applications.
 *
 * This object is:
 * - Returned in API responses
 * - Received in API requests
 *
 * Null values are excluded from JSON responses.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDTO {

    /**
     * Unique identifier of the event.
     * Included to support ID-based operations (GET, PUT, DELETE).
     */
    private Long id;

    /**
     * Name of the event.
     */
    private String name;

    /**
     * Detailed description of the event.
     */
    private String description;

    /**
     * Price of the event ticket.
     */
    private BigDecimal ticketPrice;

    /**
     * Category of the event (e.g., Technology, Music, Business).
     */
    private String category;

    /**
     * Indicates whether the event is currently active.
     */
    private Boolean active;

    /**
     * Date and time when the event will take place.
     */
    private LocalDateTime eventDate;

    /**
     * Default constructor required for JSON deserialization.
     */
    public EventDTO() {}

    /**
     * Full constructor used for mapping entity data to DTO.
     *
     * @param id          event ID
     * @param name        event name
     * @param description event description
     * @param ticketPrice ticket price
     * @param category    event category
     * @param active      event active status
     * @param eventDate   event date and time
     */
    public EventDTO(Long id,
                    String name,
                    String description,
                    BigDecimal ticketPrice,
                    String category,
                    Boolean active,
                    LocalDateTime eventDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ticketPrice = ticketPrice;
        this.category = category;
        this.active = active;
        this.eventDate = eventDate;
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
}