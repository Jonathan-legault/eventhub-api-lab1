package com.eventhub.eventhubapi.repository.impl;

import com.eventhub.eventhubapi.model.Event;
import com.eventhub.eventhubapi.repository.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * EventRepositoryImpl
 *
 * Concrete implementation of the EventRepository interface.
 *
 * This implementation uses in-memory storage (HashMap)
 * instead of a database. It simulates database behavior
 * for Lab 1 purposes.
 *
 * Key: Event ID (Long)
 * Value: Event object
 */
@Repository
public class EventRepositoryImpl implements EventRepository {

    /**
     * In-memory storage for events.
     * Maps event ID to Event object.
     */
    private final Map<Long, Event> events = new HashMap<>();

    /**
     * ID generator used to simulate auto-increment
     * primary key behavior similar to a database.
     */
    private final AtomicLong idGenerator = new AtomicLong(1);

    /**
     * Retrieves all stored events.
     *
     * @return list of all events in memory
     */
    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events.values());
    }

    /**
     * Retrieves a single event by ID.
     *
     * @param id the unique identifier of the event
     * @return the matching Event or null if not found
     */
    @Override
    public Event findById(Long id) {
        return events.get(id);
    }

    /**
     * Saves a new event or updates an existing one.
     *
     * If the event ID is null, a new ID is generated.
     * If the ID exists, the event is updated.
     *
     * @param event the event to save
     * @return the saved event with assigned ID
     */
    @Override
    public Event save(Event event) {
        if (event.getId() == null) {
            event.setId(idGenerator.getAndIncrement());
        }
        events.put(event.getId(), event);
        return event;
    }

    /**
     * Deletes an event by its ID.
     *
     * @param id the ID of the event to delete
     */
    @Override
    public void delete(Long id) {
        events.remove(id);
    }
}