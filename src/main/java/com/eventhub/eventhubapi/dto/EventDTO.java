package com.eventhub.eventhubapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * DTO used to transfer event data between the API and the client.
 * This object is typically returned in API responses when events
 * are retrieved from the system.
 *
 * Null values are excluded from JSON responses.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDTO {

    // unique identifier of the event
    private Long id;

    // name of the event
    private String name;

    // description of the event
    private String description;

    // ticket price for the event
    private BigDecimal ticketPrice;

    // category the event belongs to
    private String category;

    // indicates whether the event is active
    private Boolean active;

    // date and time when the event takes place
    private LocalDateTime eventDate;

    // default constructor (needed for JSON serialization/deserialization)
    public EventDTO() {}

    // constructor used when mapping event data to this DTO
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
}