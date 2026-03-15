package com.eventhub.eventhubapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * Model class representing an event in the EventHub system.
 * This object stores event data used internally by the application.
 *
 * JSON rules:
 * - Null fields are not included in responses.
 * - Date fields follow the ISO date format.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {

    // unique identifier for the event
    private Long id;

    // name of the event
    private String name;

    // description of the event
    private String description;

    // ticket price for attending the event
    private BigDecimal ticketPrice;

    // category the event belongs to
    private String category;

    // indicates if the event is currently active
    private Boolean active;

    // date and time the event will take place
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime eventDate;

    // timestamp when the event was created
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    // timestamp when the event was last updated
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    // default constructor
    public Event() {}

    // constructor used to create an event object with values
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

    // getters and setters

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