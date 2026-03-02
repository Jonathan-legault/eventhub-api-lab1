package com.eventhub.eventhubapi.repository;

import com.eventhub.eventhubapi.model.Event;
import java.util.List;

/**
 * EventRepository
 *
 * Repository interface defining CRUD operations
 * for Event persistence.
 *
 * This abstraction allows different implementations
 * (e.g., in-memory, database, JPA) without changing
 * business logic.
 */
public interface EventRepository {

    /**
     * Retrieves all stored events.
     *
     * @return list of all events
     */
    List<Event> findAll();

    /**
     * Retrieves a specific event by its ID.
     *
     * @param id the unique identifier of the event
     * @return the matching Event, or null if not found
     */
    Event findById(Long id);

    /**
     * Saves a new event or updates an existing one.
     *
     * @param event the event to persist
     * @return the saved event
     */
    Event save(Event event);

    /**
     * Deletes an event by its ID.
     *
     * @param id the unique identifier of the event
     */
    void delete(Long id);
}