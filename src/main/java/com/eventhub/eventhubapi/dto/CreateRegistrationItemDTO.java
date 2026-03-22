package com.eventhub.eventhubapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO used when adding an item to a registration.
 * Represents a single event and the number of tickets requested.
 */
public class CreateRegistrationItemDTO {

    /**
     * ID of the event being registered.
     */
    @NotNull(message = "Event ID is required")
    private Long eventId;

    /**
     * Number of tickets for the event.
     * Must be at least 1.
     */
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    /**
     * Default constructor required for JSON deserialization.
     */
    public CreateRegistrationItemDTO() {
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}