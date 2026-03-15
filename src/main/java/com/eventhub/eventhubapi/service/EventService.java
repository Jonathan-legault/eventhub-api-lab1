package com.eventhub.eventhubapi.service;

import com.eventhub.eventhubapi.dto.CreateEventDTO;
import com.eventhub.eventhubapi.dto.EventDTO;

import java.util.List;

/*
 * Service interface for event-related operations.
 * Defines the main actions that can be performed on events,
 * such as retrieving, creating, updating, and deleting them.
 *
 * The implementation of these methods is provided
 * in EventServiceImpl.
 */
public interface EventService {

    /*
     * Returns a list of events with optional filtering,
     * sorting, and pagination.
     */
    List<EventDTO> getAllEvents(
            int page,
            int size,
            String sort,
            String category,
            Double minPrice,
            Double maxPrice,
            String startDate,
            String endDate
    );

    /*
     * Retrieves a specific event by its ID.
     */
    EventDTO getEventById(Long id);

    /*
     * Creates a new event.
     */
    EventDTO createEvent(CreateEventDTO dto);

    /*
     * Updates an existing event.
     */
    EventDTO updateEvent(Long id, CreateEventDTO dto);

    /*
     * Deletes an event based on its ID.
     */
    void deleteEvent(Long id);
}