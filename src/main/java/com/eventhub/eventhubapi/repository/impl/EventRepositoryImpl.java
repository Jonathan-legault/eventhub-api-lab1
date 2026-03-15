package com.eventhub.eventhubapi.repository.impl;

import com.eventhub.eventhubapi.model.Event;
import com.eventhub.eventhubapi.repository.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private final Map<Long, Event> events = new LinkedHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events.values());
    }

    @Override
    public Event findById(Long id) {
        return events.get(id);
    }

    @Override
    public Event save(Event event) {
        if (event.getId() == null) {
            event.setId(idGenerator.getAndIncrement());
        }
        events.put(event.getId(), event);
        return event;
    }

    @Override
    public void delete(Long id) {
        events.remove(id);
    }
}