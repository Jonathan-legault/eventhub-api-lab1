package com.eventhub.eventhubapi.repository;

import com.eventhub.eventhubapi.model.Event;
import java.util.List;

/*
 * Repository interface for Event data.
 * Defines the basic CRUD operations that can be
 * performed on events.
 *
 * The actual logic is implemented in EventRepositoryImpl.
 */
public interface EventRepository {

    /*
     * Returns all events stored in the repository.
     */
    List<Event> findAll();

    /*
     * Finds an event by its unique ID.
     */
    Event findById(Long id);

    /*
     * Saves a new event or updates an existing one.
     */
    Event save(Event event);

    /*
     * Deletes an event based on its ID.
     */
    void delete(Long id);
}