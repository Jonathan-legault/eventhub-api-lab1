package com.eventhub.eventhubapi.service;

import com.eventhub.eventhubapi.dto.CreateEventDTO;
import com.eventhub.eventhubapi.dto.EventDTO;

import java.util.List;

public interface EventService {

    List<EventDTO> getAllEvents(
            int page,
            int size,
            String sort,
            String category,
            Double minPrice,
            Double maxPrice,
            String startDate,
            String endDate
    );

    EventDTO getEventById(Long id);

    EventDTO createEvent(CreateEventDTO dto);

    EventDTO updateEvent(Long id, CreateEventDTO dto);

    void deleteEvent(Long id);
}