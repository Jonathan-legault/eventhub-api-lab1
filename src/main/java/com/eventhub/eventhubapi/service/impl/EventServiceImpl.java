package com.eventhub.eventhubapi.service.impl;

import com.eventhub.eventhubapi.dto.CreateEventDTO;
import com.eventhub.eventhubapi.dto.EventDTO;
import com.eventhub.eventhubapi.exception.EventNotFoundException;
import com.eventhub.eventhubapi.model.Event;
import com.eventhub.eventhubapi.repository.EventRepository;
import com.eventhub.eventhubapi.service.EventService;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Service implementation for event-related business logic.
 * This class handles retrieving, creating, updating, deleting,
 * filtering, sorting, and paginating events.
 */
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    // constructor injection of the repository
    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    /*
     * Converts an Event entity into an EventDTO.
     * This is used when returning event data to the client.
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

    /*
     * Converts a CreateEventDTO into an Event entity.
     * createdAt and updatedAt are set to the current time.
     */
    private Event toEntity(CreateEventDTO dto) {
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

    /*
     * Returns all events with support for filtering,
     * sorting, and pagination.
     * The results are cached to improve performance.
     */
    @Override
    @Cacheable("events")
    public List<EventDTO> getAllEvents(
            int page,
            int size,
            String sort,
            String category,
            Double minPrice,
            Double maxPrice,
            String startDate,
            String endDate
    ) {

        // get all events from the repository
        List<Event> events = repository.findAll();

        // filter by category if provided
        if (category != null) {
            events = events.stream()
                    .filter(e -> e.getCategory().equalsIgnoreCase(category))
                    .toList();
        }

        // filter by minimum price if provided
        if (minPrice != null) {
            events = events.stream()
                    .filter(e -> e.getTicketPrice().doubleValue() >= minPrice)
                    .toList();
        }

        // filter by maximum price if provided
        if (maxPrice != null) {
            events =  events.stream()
                    .filter(e -> e.getTicketPrice().doubleValue() <= maxPrice)
                    .toList();
        }

        // filter by start date if provided
        if (startDate != null) {
            LocalDateTime start = LocalDateTime.parse(startDate);
            events = events.stream()
                    .filter(e -> e.getEventDate().isAfter(start))
                    .toList();
        }

        // filter by end date if provided
        if (endDate != null) {
            LocalDateTime start = LocalDateTime.parse(startDate);
            events = events.stream()
                    .filter(e -> e.getEventDate().isAfter(start))
                    .toList();
        }

        // calculate indexes for pagination
        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, events.size());

        // return empty list if the page is out of range
        if (startIndex > events.size()) {
            return List.of();
        }

        // split the sort parameter into field and direction
        String[] sortParts = sort.split(",");
        String sortField = sortParts[0];
        String sortDirection = sortParts.length > 1 ? sortParts[1] : "asc";

        Comparator<Event> comparator;

        // choose the field to sort by
        switch (sortField) {
            case "ticketPrice":
                comparator = Comparator.comparing(Event::getTicketPrice);
                break;
            case "eventDate":
                comparator = Comparator.comparing(Event::getEventDate);
                break;
            case "category":
                comparator = Comparator.comparing(Event::getCategory, String.CASE_INSENSITIVE_ORDER);
                break;
            case "name":
            default:
                comparator = Comparator.comparing(Event::getName, String.CASE_INSENSITIVE_ORDER);
                break;
        }

        // reverse sort order if descending is requested
        if ("desc".equalsIgnoreCase(sortDirection)) {
            comparator = comparator.reversed();
        }

        // sort the events
        events = events.stream()
                .sorted(comparator)
                .toList();

        // return the requested page after converting entities to DTOs
        return events.subList(startIndex, endIndex)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    /*
     * Finds an event by its ID.
     * Throws a custom exception if the event does not exist.
     */
    @Override
    public EventDTO getEventById(Long id) {
        Event event = repository.findById(id);
        if (event == null) {
            throw new EventNotFoundException("Event not found with id: " + id);
        }
        return toDTO(event);
    }

    /*
     * Creates a new event.
     * The events cache is cleared after adding a new record.
     */
    @Override
    @CacheEvict(value = "events", allEntries = true)
    public EventDTO createEvent(CreateEventDTO dto) {
        Event event = toEntity(dto);
        return toDTO(repository.save(event));
    }

    /*
     * Updates an existing event.
     * Throws an exception if the event does not exist.
     * The events cache is cleared after updating.
     */
    @Override
    @CacheEvict(value = "events", allEntries = true)
    public EventDTO updateEvent(Long id, CreateEventDTO dto) {
        Event existing = repository.findById(id);
        if (existing == null) {
            throw new EventNotFoundException("Event not found with id: " + id);
        }

        // update the existing event fields
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setTicketPrice(dto.getTicketPrice());
        existing.setCategory(dto.getCategory());
        existing.setActive(dto.getActive());
        existing.setEventDate(dto.getEventDate());
        existing.setUpdatedAt(LocalDateTime.now());

        return toDTO(repository.save(existing));
    }

    /*
     * Deletes an event by its ID.
     * Throws an exception if the event does not exist.
     * The events cache is cleared after deleting.
     */
    @Override
    @CacheEvict(value = "events", allEntries = true)
    public void deleteEvent(Long id) {
        Event existing = repository.findById(id);
        if (existing == null) {
            throw new EventNotFoundException("Event not found with id: " + id);
        }
        repository.delete(id);
    }
}