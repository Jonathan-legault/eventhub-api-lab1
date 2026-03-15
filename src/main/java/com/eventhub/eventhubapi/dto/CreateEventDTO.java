package com.eventhub.eventhubapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * DTO used when creating or updating an event.
 * It contains the data sent from the client to the API
 * and includes validation rules to ensure the data is valid.
 */
public class CreateEventDTO {

    // event name with validation rules
    @NotBlank(message = "Event name is required")
    @Size(min = 3, max = 100, message = "Event name must be between 3 and 100 characters")
    private String name;

    // optional event description
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    // ticket price for the event
    @NotNull(message = "Ticket price is required")
    @PositiveOrZero(message = "Ticket price must be positive or zero")
    private BigDecimal ticketPrice;

    // category name associated with the event
    @NotBlank(message = "Category is required")
    private String category;

    // indicates if the event is active or available
    @NotNull(message = "Active status is required")
    private Boolean active;

    // date and time when the event will occur
    @NotNull(message = "Event date is required")
    private LocalDateTime eventDate;

    // default constructor
    public CreateEventDTO() {
    }

    // getter for event name
    public String getName() {
        return name;
    }

    // setter for event name
    public void setName(String name) {
        this.name = name;
    }

    // getter for description
    public String getDescription() {
        return description;
    }

    // setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // getter for ticket price
    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    // setter for ticket price
    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    // getter for category
    public String getCategory() {
        return category;
    }

    // setter for category
    public void setCategory(String category) {
        this.category = category;
    }

    // getter for active status
    public Boolean getActive() {
        return active;
    }

    // setter for active status
    public void setActive(Boolean active) {
        this.active = active;
    }

    // getter for event date
    public LocalDateTime getEventDate() {
        return eventDate;
    }

    // setter for event date
    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }
}