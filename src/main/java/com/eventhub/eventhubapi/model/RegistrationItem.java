package com.eventhub.eventhubapi.model;

import jakarta.persistence.*;

/**
 * JPA entity representing an item within a registration.
 * Each item links one registration to one event and stores the ticket quantity.
 */
@Entity
@Table(name = "registration_items")
public class RegistrationItem {

    /**
     * Primary key (auto-generated).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Number of tickets for this event in the registration.
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * Many registration items belong to one registration.
     */
    @ManyToOne
    @JoinColumn(name = "registration_id", nullable = false)
    private Registration registration;

    /**
     * Many registration items can refer to one event.
     */
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    /**
     * Default constructor required by JPA.
     */
    public RegistrationItem() {
    }

    /**
     * Constructor used to create a registration item object.
     */
    public RegistrationItem(Long id, Integer quantity, Registration registration, Event event) {
        this.id = id;
        this.quantity = quantity;
        this.registration = registration;
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}