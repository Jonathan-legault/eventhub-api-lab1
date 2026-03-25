package com.eventhub.eventhubapi.controller;

import com.eventhub.eventhubapi.dto.CreateEventDTO;
import com.eventhub.eventhubapi.dto.EventDTO;
import com.eventhub.eventhubapi.service.EventService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing event-related API requests.
 * Supports CRUD operations, filtering, searching, and event statistics.
 */
@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService service;

    /**
     * Constructor injection of EventService.
     *
     * @param service service layer for event operations
     */
    public EventController(EventService service) {
        this.service = service;
    }

    /**
     * Returns a list of events with optional filtering, sorting, and pagination.
     * Public endpoint.
     *
     * @param page page number, default is 0
     * @param size number of records per page, default is 20
     * @param sort sort field and direction, default is "name,asc"
     * @param category optional category filter
     * @param minPrice optional minimum ticket price
     * @param maxPrice optional maximum ticket price
     * @param startDate optional start date filter
     * @param endDate optional end date filter
     * @return filtered list of events
     */
    @GetMapping
    public List<EventDTO> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "name,asc") String sort,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        return service.getAllEvents(page, size, sort, category, minPrice, maxPrice, startDate, endDate);
    }

    /**
     * Returns a single event by its ID.
     * Public endpoint.
     *
     * @param id event ID
     * @return matching event
     */
    @GetMapping("/{id}")
    public EventDTO getById(@PathVariable Long id) {
        return service.getEventById(id);
    }

    /**
     * Creates a new event.
     * ADMIN only.
     *
     * @param dto validated event request body
     * @return created event
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public EventDTO create(@Valid @RequestBody CreateEventDTO dto) {
        return service.createEvent(dto);
    }

    /**
     * Updates an existing event.
     * ADMIN only.
     *
     * @param id event ID
     * @param dto validated updated event data
     * @return updated event
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public EventDTO update(@PathVariable Long id,
                           @Valid @RequestBody CreateEventDTO dto) {
        return service.updateEvent(id, dto);
    }

    /**
     * Deletes an event by its ID.
     * ADMIN only.
     *
     * @param id event ID
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEvent(id);
    }

    /**
     * Searches events by keyword in name or description.
     * Public endpoint.
     *
     * @param keyword search keyword
     * @return list of matching events
     */
    @GetMapping("/search")
    public List<EventDTO> search(@RequestParam String keyword) {
        return service.searchEvents(keyword);
    }

    /**
     * Returns the number of registrations for each event.
     * ADMIN only.
     *
     * @return event registration count summary
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/stats/registration-counts")
    public List<String> getRegistrationCountsPerEvent() {
        return service.getRegistrationCountsPerEvent();
    }

    /**
     * Returns the total number of tickets sold for each event.
     * ADMIN only.
     *
     * @return event ticket sales summary
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/stats/tickets-sold")
    public List<String> getTotalTicketsSoldPerEvent() {
        return service.getTotalTicketsSoldPerEvent();
    }
}