package com.eventhub.eventhubapi.repository.impl;

import com.eventhub.eventhubapi.model.Event;
import com.eventhub.eventhubapi.repository.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/*
 * Repository implementation for managing Event data.
 * This repository stores events in memory using a Map
 * instead of a database.
 */
@Repository
public class EventRepositoryImpl implements EventRepository {

    // in-memory storage for events (id -> Event)
    private final Map<Long, Event> events = new LinkedHashMap<>();

    // generates unique IDs for new events
    private final AtomicLong idGenerator = new AtomicLong(1);

    /*
     * Returns all events currently stored.
     */
    @Override
    public List<Event> findAll() {

        // convert map values to a list
        return new ArrayList<>(events.values());
    }

    /*
     * Finds a specific event by its ID.
     */
    @Override
    public Event findById(Long id) {

        // return the event from the map
        return events.get(id);
    }

    /*
     * Saves a new event or updates an existing one.
     * If the event does not have an ID yet,
     * a new one will be generated.
     */
    @Override
    public Event save(Event event) {

        // generate ID for new events
        if (event.getId() == null) {
            event.setId(idGenerator.getAndIncrement());
        }

        // store the event in the map
        events.put(event.getId(), event);

        return event;
    }

    /*
     * Deletes an event based on its ID.
     */
    @Override
    public void delete(Long id) {

        // remove the event from the map
        events.remove(id);
    }
}