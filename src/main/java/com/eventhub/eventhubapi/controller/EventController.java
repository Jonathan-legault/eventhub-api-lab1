package com.eventhub.eventhubapi.controller;


import com.eventhub.eventhubapi.dto.CreateEventDTO;
import com.eventhub.eventhubapi.dto.EventDTO;
import com.eventhub.eventhubapi.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService service;
    public EventController(EventService service){
        this.service = service;
    }

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

    @GetMapping("/{id}")
    public EventDTO getById(@PathVariable Long id){
        return service.getEventById(id);
    }

    @PostMapping
    public EventDTO create(@Valid @RequestBody CreateEventDTO dto) {
        return service.createEvent(dto);
    }

    @PutMapping("/{id}")
    public EventDTO update(@PathVariable Long id,
                           @Valid @RequestBody CreateEventDTO dto) {
        return service.updateEvent(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEvent(id);
    }
}