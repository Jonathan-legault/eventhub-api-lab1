package com.eventhub.eventhubapi.repository;

import com.eventhub.eventhubapi.model.Event;
import java.util.List;

public interface EventRepository {

    List<Event> findAll();
    Event findById(Long id);
    Event save(Event event);
    void delete(Long id);
}