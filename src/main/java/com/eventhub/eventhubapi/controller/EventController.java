package com.eventhub.eventhubapi.controller;

import com.eventhub.eventhubapi.dto.CreateEventDTO;
import com.eventhub.eventhubapi.dto.EventDTO;
import com.eventhub.eventhubapi.service.EventService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * REST controller that handles API requests related to events.
 * It allows clients to create, read, update, delete and filter events.
 */
@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService service;

    // constructor injection of EventService
    public EventController(EventService service){
        this.service = service;
    }

    /*
     * GET /api/v1/events
     * Returns a list of events with optional filtering, sorting and pagination.
     *
     * page - page number (default 0)
     * size - number of results per page
     * sort - sorting field and direction
     * category - optional category filter
     * minPrice / maxPrice - optional price range
     * startDate / endDate - optional date range
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

    /*
     * GET /api/v1/events/{id}
     * Returns a single event based on its ID.
     */
    @GetMapping("/{id}")
    public EventDTO getById(@PathVariable Long id){
        return service.getEventById(id);
    }

    /*
     * POST /api/v1/events
     * Creates a new event.
     * @Valid ensures the request body follows validation rules in CreateEventDTO.
     */
    @PostMapping
    public EventDTO create(@Valid @RequestBody CreateEventDTO dto) {
        return service.createEvent(dto);
    }

    /*
     * PUT /api/v1/events/{id}
     * Updates an existing event.
     */
    @PutMapping("/{id}")
    public EventDTO update(@PathVariable Long id,
                           @Valid @RequestBody CreateEventDTO dto) {
        return service.updateEvent(id, dto);
    }

    /*
     * DELETE /api/v1/events/{id}
     * Deletes an event from the system.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEvent(id);
    }
}