package com.eventhub.eventhubapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO used when returning registration item data to the client.
 * Represents a single event included in a registration.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationItemDTO {

    /**
     * ID of the event.
     */
    private Long eventId;

    /**
     * Name of the event.
     */
    private String eventName;

    /**
     * Quantity of tickets for the event.
     */
    private Integer quantity;

    /**
     * Default constructor required for JSON serialization/deserialization.
     */
    public RegistrationItemDTO() {
    }

    /**
     * Constructor used to map registration item data into the DTO.
     */
    public RegistrationItemDTO(Long eventId, String eventName, Integer quantity) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.quantity = quantity;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}