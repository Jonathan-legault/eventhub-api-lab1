package com.eventhub.eventhubapi.service;

import com.eventhub.eventhubapi.dto.EventDTO;
import java.util.List;

public interface EventService {

    List<EventDTO> getAllEvents();
    EventDTO getEventById(Long id);
    EventDTO createEvent(EventDTO dto);
    EventDTO updateEvent(Long id, EventDTO dto);
    void deleteEvent(Long id);
}