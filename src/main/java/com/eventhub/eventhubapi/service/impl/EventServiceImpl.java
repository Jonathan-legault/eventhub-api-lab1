package com.eventhub.eventhubapi.service.impl;

import com.eventhub.eventhubapi.dto.EventDTO;
import com.eventhub.eventhubapi.model.Event;
import com.eventhub.eventhubapi.repository.EventRepository;
import com.eventhub.eventhubapi.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    private EventDTO toDTO(Event event) {
        return new EventDTO(
                event.getName(),
                event.getDescription(),
                event.getTicketPrice(),
                event.getCategory(),
                // ✅ fixed: getActive()
                event.getActive(),
                event.getEventDate()
        );
    }

    private Event toEntity(EventDTO dto) {
        return new Event(
                null,
                dto.getName(),
                dto.getDescription(),
                dto.getTicketPrice(),
                dto.getCategory(),
                // ✅ fixed: getActive()
                dto.getActive(),
                dto.getEventDate(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EventDTO getEventById(Long id) {
        Event event = repository.findById(id);
        return event != null ? toDTO(event) : null;
    }

    @Override
    public EventDTO createEvent(EventDTO dto) {
        Event event = toEntity(dto);
        return toDTO(repository.save(event));
    }

    @Override
    public EventDTO updateEvent(Long id, EventDTO dto) {
        Event existing = repository.findById(id);
        if (existing == null) return null;

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setTicketPrice(dto.getTicketPrice());
        existing.setCategory(dto.getCategory());

        // ✅ fixed: setActive(...)
        existing.setActive(dto.getActive());

        existing.setEventDate(dto.getEventDate());
        existing.setUpdatedAt(LocalDateTime.now());

        return toDTO(repository.save(existing));
    }

    @Override
    public void deleteEvent(Long id) {
        repository.delete(id);
    }
}