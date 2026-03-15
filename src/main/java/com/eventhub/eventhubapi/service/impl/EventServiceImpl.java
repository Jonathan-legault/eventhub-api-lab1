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

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

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

        //Filter by category
        if (category != null) {
            events = events.stream()
                    .filter(e -> e.getCategory().equalsIgnoreCase(category))
                    .toList();
        }

        //Filter by minimum price
        if (minPrice != null) {
            events = events.stream()
                    .filter(e -> e.getTicketPrice().doubleValue() >= minPrice)
                    .toList();
        }

        //Filter by maximum price
        if (maxPrice != null) {
            events =  events.stream()
                    .filter(e -> e.getTicketPrice().doubleValue() <= maxPrice)
                    .toList();
        }


        // Filter by start date
        if (startDate != null) {
            LocalDateTime start = LocalDateTime.parse(startDate);
            events = events.stream()
                    .filter(e -> e.getEventDate().isAfter(start))
                    .toList();
        }

        // Filter by end date
        if (endDate != null) {
            LocalDateTime start = LocalDateTime.parse(startDate);
            events = events.stream()
                    .filter(e -> e.getEventDate().isAfter(start))
                    .toList();
        }

        //Pagination
        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, events.size());

        if (startIndex > events.size()) {
            return List.of();
        }

        // Sorting
        String[] sortParts = sort.split(",");
        String sortField = sortParts[0];
        String sortDirection = sortParts.length > 1 ? sortParts[1] : "asc";

        Comparator<Event> comparator;

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

        if ("desc".equalsIgnoreCase(sortDirection)) {
            comparator = comparator.reversed();
        }

        events = events.stream()
                .sorted(comparator)
                .toList();

        return events.subList(startIndex, endIndex)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public EventDTO getEventById(Long id) {
        Event event = repository.findById(id);
        if (event == null) {
            throw new EventNotFoundException("Event not found with id: " + id);
        }
        return toDTO(event);
    }

    @Override
    @CacheEvict(value = "events", allEntries = true)
    public EventDTO createEvent(CreateEventDTO dto) {
        Event event = toEntity(dto);
        return toDTO(repository.save(event));
    }

    @Override
    @CacheEvict(value = "events", allEntries = true)
    public EventDTO updateEvent(Long id, CreateEventDTO dto) {
        Event existing = repository.findById(id);
        if (existing == null) {
            throw new EventNotFoundException("Event not found with id: " + id);
        }

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setTicketPrice(dto.getTicketPrice());
        existing.setCategory(dto.getCategory());
        existing.setActive(dto.getActive());
        existing.setEventDate(dto.getEventDate());
        existing.setUpdatedAt(LocalDateTime.now());

        return toDTO(repository.save(existing));
    }

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