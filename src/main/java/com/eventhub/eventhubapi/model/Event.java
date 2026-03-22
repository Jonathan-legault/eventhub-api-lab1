package com.eventhub.eventhubapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * JPA entity representing an event in the EventHub system.
 * Maps to the "events" table in the database.
 *
 * JSON rules:
 * - Null fields are excluded from responses.
 * - Date fields use ISO date-time format.
 */
@Entity
@Table(name = "events")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {

    /**
     * Primary key (auto-generated).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the event.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Description of the event.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Ticket price for the event.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal ticketPrice;

    /**
     * Many events can belong to one category.
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /**
     * Indicates whether the event is active.
     */
    @Column(nullable = false)
    private Boolean active;

    /**
     * Date and time when the event takes place.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime eventDate;

    /**
     * Timestamp when the event was created.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    /**
     * Timestamp when the event was last updated.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    /**
     * Default constructor required by JPA.
     */
    public Event() {
    }

    /**
     * Full constructor used when creating or mapping event objects.
     */
    public Event(Long id,
                 String name,
                 String description,
                 BigDecimal ticketPrice,
                 Category category,
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}