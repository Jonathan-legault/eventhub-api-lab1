package com.eventhub.eventhubapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventDTO {

    private String name;
    private String description;
    private BigDecimal ticketPrice;
    private String category;
    private Boolean active;
    private LocalDateTime eventDate;

    public EventDTO() {}

    public EventDTO(String name,
                    String description,
                    BigDecimal ticketPrice,
                    String category,
                    Boolean active,
                    LocalDateTime eventDate) {

        this.name = name;
        this.description = description;
        this.ticketPrice = ticketPrice;
        this.category = category;
        this.active = active;
        this.eventDate = eventDate;
    }

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