package com.eventhub.eventhubapi.service;

import com.eventhub.eventhubapi.dto.CreateEventDTO;
import com.eventhub.eventhubapi.dto.EventDTO;

import java.util.List;

/**
 * Service interface for event-related business operations.
 * Defines methods for managing, searching, filtering,
 * and reporting event data.
 */
public interface EventService {

    /**
     * Returns events with optional filtering, sorting, and pagination.
     *
     * @param page page number
     * @param size page size
     * @param sort sort field and direction
     * @param category optional category filter
     * @param minPrice optional minimum price filter
     * @param maxPrice optional maximum price filter
     * @param startDate optional start date filter
     * @param endDate optional end date filter
     * @return list of matching events
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

    /**
     * Returns a single event by ID.
     *
     * @param id event ID
     * @return matching event
     */
    EventDTO getEventById(Long id);

    /**
     * Creates a new event.
     *
     * @param dto event creation request
     * @return created event
     */
    EventDTO createEvent(CreateEventDTO dto);

    /**
     * Updates an existing event.
     *
     * @param id event ID
     * @param dto updated event data
     * @return updated event
     */
    EventDTO updateEvent(Long id, CreateEventDTO dto);

    /**
     * Deletes an event by ID.
     *
     * @param id event ID
     */
    void deleteEvent(Long id);

    /**
     * Searches events by keyword.
     *
     * @param keyword search keyword
     * @return list of matching events
     */
    List<EventDTO> searchEvents(String keyword);

    /**
     * Returns registration counts grouped by event.
     *
     * @return list of event registration summaries
     */
    List<String> getRegistrationCountsPerEvent();

    /**
     * Returns total tickets sold grouped by event.
     *
     * @return list of event ticket sales summaries
     */
    List<String> getTotalTicketsSoldPerEvent();
}