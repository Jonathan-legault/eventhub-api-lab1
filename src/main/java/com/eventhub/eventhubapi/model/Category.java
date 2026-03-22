package com.eventhub.eventhubapi.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 * JPA Entity representing an event category.
 * Maps to the "categories" table in the database.
 */
@Entity
@Table(name = "categories")
public class Category {

    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Category name (must be unique and not null)
    @Column(nullable = false, unique = true)
    private String name;

    // One category can have many events
    @OneToMany(mappedBy = "category")
    private List<Event> events = new ArrayList<>();

    // Default constructor (required by JPA)
    public Category() {
    }

    // Constructor with fields
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters

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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}