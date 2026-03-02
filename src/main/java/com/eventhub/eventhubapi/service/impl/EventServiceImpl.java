package com.eventhub.eventhubapi.service.impl;

import com.eventhub.eventhubapi.dto.EventDTO;
import com.eventhub.eventhubapi.model.Event;
import com.eventhub.eventhubapi.repository.EventRepository;
import com.eventhub.eventhubapi.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * EventServiceImpl
 *
 * Service layer implementation responsible for
 * handling business logic related to Event operations.
 *
 * This layer acts as a bridge between the Controller
 * and Repository layers.
 */
@Service
public class EventServiceImpl implements EventService {

    /**
     * Repository dependency used for data persistence.
     */
    private final EventRepository repository;

    /**
     * Constructor-based dependency injection.
     *
     * @param repository the event repository implementation
     */
    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    /**
     * Converts Event entity to EventDTO.
     *
     * @param event the Event entity
     * @return corresponding EventDTO
     */
    private EventDTO toDTO(Event event) {
        return new EventDTO(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getTicketPrice(),
                event.getCategory(),
                event.getActive(),
                event.getEventDate()
        );
    }

    /**
     * Converts EventDTO to Event entity.
     *
     * ID is set to null so that the repository
     * generates a new one if necessary.
     *
     * @param dto the EventDTO
     * @return corresponding Event entity
     */
    private Event toEntity(EventDTO dto) {
        return new Event(
                null,
                dto.getName(),
                dto.getDescription(),
                dto.getTicketPrice(),
                dto.getCategory(),
                dto.getActive(),
                dto.getEventDate(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    /**
     * Retrieves all events.
     *
     * @return list of EventDTO objects
     */
    @Override
    public List<EventDTO> getAllEvents() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an event by ID.
     *
     * @param id event identifier
     * @return EventDTO if found, otherwise null
     */
    @Override
    public EventDTO getEventById(Long id) {
        Event event = repository.findById(id);
        return event != null ? toDTO(event) : null;
    }

    /**
     * Creates a new event.
     *
     * @param dto event data received from client
     * @return created EventDTO
     */
    @Override
    public EventDTO createEvent(EventDTO dto) {
        Event event = toEntity(dto);
        return toDTO(repository.save(event));
    }

    /**
     * Updates an existing event.
     *
     * @param id  event identifier
     * @param dto updated event data
     * @return updated EventDTO or null if not found
     */
    @Override
    public EventDTO updateEvent(Long id, EventDTO dto) {
        Event existing = repository.findById(id);
        if (existing == null) return null;

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setTicketPrice(dto.getTicketPrice());
        existing.setCategory(dto.getCategory());
        existing.setActive(dto.getActive());
        existing.setEventDate(dto.getEventDate());
        existing.setUpdatedAt(LocalDateTime.now());

        return toDTO(repository.save(existing));
    }

    /**
     * Deletes an event by ID.
     *
     * @param id event identifier
     */
    @Override
    public void deleteEvent(Long id) {
        repository.delete(id);
    }
}