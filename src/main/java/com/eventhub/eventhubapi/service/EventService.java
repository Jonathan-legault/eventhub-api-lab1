package com.eventhub.eventhubapi.service;

import com.eventhub.eventhubapi.dto.EventDTO;
import java.util.List;

/**
 * EventService
 *
 * Service interface defining business logic operations
 * related to Event management.
 *
 * This layer abstracts the controller from the repository
 * implementation and allows flexible extension in the future.
 */
public interface EventService {

    /**
     * Retrieves all events.
     *
     * @return list of EventDTO objects
     */
    List<EventDTO> getAllEvents();

    /**
     * Retrieves a specific event by its ID.
     *
     * @param id the unique identifier of the event
     * @return matching EventDTO or null if not found
     */
    EventDTO getEventById(Long id);

    /**
     * Creates a new event.
     *
     * @param dto the event data received from the client
     * @return the created EventDTO
     */
    EventDTO createEvent(EventDTO dto);

    /**
     * Updates an existing event.
     *
     * @param id  the ID of the event to update
     * @param dto the updated event data
     * @return updated EventDTO or null if not found
     */
    EventDTO updateEvent(Long id, EventDTO dto);

    /**
     * Deletes an event by its ID.
     *
     * @param id the unique identifier of the event
     */
    void deleteEvent(Long id);
}