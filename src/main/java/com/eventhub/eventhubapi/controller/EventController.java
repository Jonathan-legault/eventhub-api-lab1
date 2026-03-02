package com.eventhub.eventhubapi.controller;

import com.eventhub.eventhubapi.dto.EventDTO;
import com.eventhub.eventhubapi.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * EventController
 *
 * REST Controller responsible for handling HTTP requests
 * related to Event operations.
 *
 * Base URL: /api/events
 *
 * Provides full CRUD functionality using the EventService layer.
 */
@RestController
@RequestMapping("/api/events")
public class EventController {

    /**
     * Service layer dependency.
     * Injected via constructor (Dependency Injection).
     */
    private final EventService service;

    /**
     * Constructor-based injection of EventService.
     *
     * @param service the service layer handling business logic
     */
    public EventController(EventService service) {
        this.service = service;
    }

    /**
     * Retrieves all events.
     *
     * Endpoint: GET /api/events
     *
     * @return list of all events
     */
    @GetMapping
    public List<EventDTO> getAll() {
        return service.getAllEvents();
    }

    /**
     * Retrieves a specific event by its ID.
     *
     * Endpoint: GET /api/events/{id}
     *
     * @param id the unique identifier of the event
     * @return the matching EventDTO
     */
    @GetMapping("/{id}")
    public EventDTO getById(@PathVariable Long id) {
        return service.getEventById(id);
    }

    /**
     * Creates a new event.
     *
     * Endpoint: POST /api/events
     *
     * @param dto the event data received in request body
     * @return the created event
     */
    @PostMapping
    public EventDTO create(@RequestBody EventDTO dto) {
        return service.createEvent(dto);
    }

    /**
     * Updates an existing event.
     *
     * Endpoint: PUT /api/events/{id}
     *
     * @param id  the ID of the event to update
     * @param dto the updated event data
     * @return the updated event
     */
    @PutMapping("/{id}")
    public EventDTO update(@PathVariable Long id,
                           @RequestBody EventDTO dto) {
        return service.updateEvent(id, dto);
    }

    /**
     * Deletes an event by ID.
     *
     * Endpoint: DELETE /api/events/{id}
     *
     * @param id the ID of the event to delete
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEvent(id);
    }
}