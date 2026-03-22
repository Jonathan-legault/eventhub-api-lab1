package com.eventhub.eventhubapi.service.impl;

import com.eventhub.eventhubapi.dto.CreateEventDTO;
import com.eventhub.eventhubapi.dto.EventDTO;
import com.eventhub.eventhubapi.exception.EventNotFoundException;
import com.eventhub.eventhubapi.model.Category;
import com.eventhub.eventhubapi.model.Event;
import com.eventhub.eventhubapi.repository.CategoryRepository;
import com.eventhub.eventhubapi.repository.EventRepository;
import com.eventhub.eventhubapi.service.EventService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/*
 * Service implementation for event-related business logic.
 * Handles retrieving, creating, updating, deleting,
 * filtering, sorting, and paginating events.
 */
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;
    private final CategoryRepository categoryRepository;

    // constructor injection
    public EventServiceImpl(EventRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    /*
     * Converts an Event entity into an EventDTO.
     */
    private EventDTO toDTO(Event event) {
        return new EventDTO(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getTicketPrice(),
                event.getCategory() != null ? event.getCategory().getName() : null,
                event.getActive(),
                event.getEventDate()
        );
    }

    /*
     * Converts a CreateEventDTO into an Event entity.
     * Looks up the matching Category entity by name.
     */
    private Event toEntity(CreateEventDTO dto) {
        Category category = categoryRepository.findByNameIgnoreCase(dto.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategory()));

        return new Event(
                null,
                dto.getName(),
                dto.getDescription(),
                dto.getTicketPrice(),
                category,
                dto.getActive(),
                dto.getEventDate(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    /*
     * Returns all events with filtering, sorting, and pagination.
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
        List<Event> events = repository.findAll();

        // filter by category
        if (category != null && !category.isBlank()) {
            events = events.stream()
                    .filter(e -> e.getCategory() != null
                            && e.getCategory().getName().equalsIgnoreCase(category))
                    .toList();
        }

        // filter by minimum price
        if (minPrice != null) {
            events = events.stream()
                    .filter(e -> e.getTicketPrice() != null
                            && e.getTicketPrice().doubleValue() >= minPrice)
                    .toList();
        }

        // filter by maximum price
        if (maxPrice != null) {
            events = events.stream()
                    .filter(e -> e.getTicketPrice() != null
                            && e.getTicketPrice().doubleValue() <= maxPrice)
                    .toList();
        }

        // filter by start date
        if (startDate != null && !startDate.isBlank()) {
            LocalDateTime start = LocalDateTime.parse(startDate);
            events = events.stream()
                    .filter(e -> e.getEventDate() != null && !e.getEventDate().isBefore(start))
                    .toList();
        }

        // filter by end date
        if (endDate != null && !endDate.isBlank()) {
            LocalDateTime end = LocalDateTime.parse(endDate);
            events = events.stream()
                    .filter(e -> e.getEventDate() != null && !e.getEventDate().isAfter(end))
                    .toList();
        }

        // sorting
        String[] sortParts = sort.split(",");
        String sortField = sortParts[0];
        String sortDirection = sortParts.length > 1 ? sortParts[1] : "asc";

        Comparator<Event> comparator;

        switch (sortField) {
            case "ticketPrice":
                comparator = Comparator.comparing(Event::getTicketPrice,
                        Comparator.nullsLast(Comparator.naturalOrder()));
                break;
            case "eventDate":
                comparator = Comparator.comparing(Event::getEventDate,
                        Comparator.nullsLast(Comparator.naturalOrder()));
                break;
            case "category":
                comparator = Comparator.comparing(
                        e -> e.getCategory() != null ? e.getCategory().getName() : "",
                        String.CASE_INSENSITIVE_ORDER
                );
                break;
            case "name":
            default:
                comparator = Comparator.comparing(
                        Event::getName,
                        Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)
                );
                break;
        }

        if ("desc".equalsIgnoreCase(sortDirection)) {
            comparator = comparator.reversed();
        }

        events = events.stream()
                .sorted(comparator)
                .toList();

        // pagination
        int startIndex = page * size;
        if (startIndex >= events.size()) {
            return List.of();
        }

        int endIndex = Math.min(startIndex + size, events.size());

        return events.subList(startIndex, endIndex)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    /*
     * Finds an event by ID.
     */
    @Override
    public EventDTO getEventById(Long id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));
        return toDTO(event);
    }

    /*
     * Creates a new event.
     */
    @Override
    @CacheEvict(value = "events", allEntries = true)
    public EventDTO createEvent(CreateEventDTO dto) {

        Event existing = repository.findByNameIgnoreCaseAndEventDate(
                dto.getName(),
                dto.getEventDate()
        ).orElse(null);

        if (existing != null) {
            return toDTO(existing);
        }

        Event event = toEntity(dto);
        return toDTO(repository.save(event));
    }

    /*
     * Updates an existing event.
     */
    @Override
    @CacheEvict(value = "events", allEntries = true)
    public EventDTO updateEvent(Long id, CreateEventDTO dto) {
        Event existing = repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));

        Category category = categoryRepository.findByNameIgnoreCase(dto.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategory()));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setTicketPrice(dto.getTicketPrice());
        existing.setCategory(category);
        existing.setActive(dto.getActive());
        existing.setEventDate(dto.getEventDate());
        existing.setUpdatedAt(LocalDateTime.now());

        return toDTO(repository.save(existing));
    }

    /*
     * Deletes an event by ID.
     */
    @Override
    @CacheEvict(value = "events", allEntries = true)
    public void deleteEvent(Long id) {
        if (!repository.existsById(id)) {
            throw new EventNotFoundException("Event not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<EventDTO> searchEvents(String keyword) {
        return repository.searchByKeyword(keyword)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<String> getRegistrationCountsPerEvent() {
        return repository.countRegistrationsPerEvent()
                .stream()
                .map(row -> row[0] + " - registrations: " + row[1])
                .toList();
    }

    @Override
    public List<String> getTotalTicketsSoldPerEvent() {
        return repository.totalTicketsSoldPerEvent()
                .stream()
                .map(row -> row[0] + " - tickets sold: " + row[1])
                .toList();
    }
}