package com.eventhub.eventhubapi.controller;

import com.eventhub.eventhubapi.dto.EventDTO;
import com.eventhub.eventhubapi.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventDTO> getAll() {
        return service.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventDTO getById(@PathVariable Long id) {
        return service.getEventById(id);
    }

    @PostMapping
    public EventDTO create(@RequestBody EventDTO dto) {
        return service.createEvent(dto);
    }

    @PutMapping("/{id}")
    public EventDTO update(@PathVariable Long id,
                           @RequestBody EventDTO dto) {
        return service.updateEvent(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEvent(id);
    }
}