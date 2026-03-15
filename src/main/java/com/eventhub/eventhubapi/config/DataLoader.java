package com.eventhub.eventhubapi.config;

import com.eventhub.eventhubapi.dto.CreateEventDTO;
import com.eventhub.eventhubapi.dto.CategoryDTO;
import com.eventhub.eventhubapi.service.CategoryService;
import com.eventhub.eventhubapi.service.EventService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final EventService eventService;
    private final CategoryService categoryService;

    public DataLoader(EventService eventService, CategoryService categoryService) {
        this.eventService = eventService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) {
        loadCategories();
        loadEvents();
    }

    private void loadCategories() {
        categoryService.createCategory(new CategoryDTO(null, "Conference"));
        categoryService.createCategory(new CategoryDTO(null, "Workshop"));
        categoryService.createCategory(new CategoryDTO(null, "Concert"));
        categoryService.createCategory(new CategoryDTO(null, "Business"));
        categoryService.createCategory(new CategoryDTO(null, "Gaming"));
        categoryService.createCategory(new CategoryDTO(null, "Meetup"));
    }

    private void loadEvents() {
        eventService.createEvent(buildEvent(
                "Spring Boot Conference",
                "Developer conference for Spring Boot.",
                new BigDecimal("150"),
                "Conference",
                true,
                LocalDateTime.of(2026, 6, 1, 10, 0)
        ));

        eventService.createEvent(buildEvent(
                "Spring Boot Hands-On Workshop",
                "Practical workshop on building REST APIs with Spring Boot.",
                new BigDecimal("75"),
                "Workshop",
                true,
                LocalDateTime.of(2026, 5, 10, 13, 0)
        ));

        eventService.createEvent(buildEvent(
                "Summer Rock Festival",
                "Outdoor rock concert featuring multiple bands.",
                new BigDecimal("120"),
                "Concert",
                true,
                LocalDateTime.of(2026, 7, 20, 18, 30)
        ));

        eventService.createEvent(buildEvent(
                "Global Tech Expo",
                "Annual technology exhibition showcasing the latest innovations.",
                new BigDecimal("180"),
                "Conference",
                true,
                LocalDateTime.of(2026, 8, 12, 9, 30)
        ));

        eventService.createEvent(buildEvent(
                "Indie Night Live",
                "Live indie music performances from upcoming artists.",
                new BigDecimal("65"),
                "Concert",
                true,
                LocalDateTime.of(2026, 4, 18, 20, 0)
        ));

        eventService.createEvent(buildEvent(
                "Full Stack Coding Bootcamp",
                "Intensive workshop covering modern full-stack development.",
                new BigDecimal("95"),
                "Workshop",
                true,
                LocalDateTime.of(2026, 5, 25, 10, 0)
        ));

        eventService.createEvent(buildEvent(
                "Startup Pitch Day",
                "Entrepreneurs pitch their startups to investors.",
                new BigDecimal("50"),
                "Business",
                true,
                LocalDateTime.of(2026, 9, 3, 14, 0)
        ));

        eventService.createEvent(buildEvent(
                "Gaming Championship",
                "Competitive gaming tournament with multiple titles.",
                new BigDecimal("110"),
                "Gaming",
                true,
                LocalDateTime.of(2026, 6, 22, 16, 0)
        ));

        eventService.createEvent(buildEvent(
                "Electronic Dance Festival",
                "Large EDM festival featuring international DJs.",
                new BigDecimal("200"),
                "Concert",
                true,
                LocalDateTime.of(2026, 7, 30, 19, 0)
        ));

        eventService.createEvent(buildEvent(
                "Local Developer Meetup",
                "Networking event for software developers.",
                new BigDecimal("20"),
                "Meetup",
                true,
                LocalDateTime.of(2026, 3, 28, 18, 0)
        ));

        eventService.createEvent(buildEvent(
                "Data Science with Python",
                "Hands-on workshop for machine learning and data analysis.",
                new BigDecimal("130"),
                "Workshop",
                true,
                LocalDateTime.of(2026, 10, 10, 11, 0)
        ));
    }

    private CreateEventDTO buildEvent(
            String name,
            String description,
            BigDecimal ticketPrice,
            String category,
            Boolean active,
            LocalDateTime eventDate
    ) {
        CreateEventDTO dto = new CreateEventDTO();
        dto.setName(name);
        dto.setDescription(description);
        dto.setTicketPrice(ticketPrice);
        dto.setCategory(category);
        dto.setActive(active);
        dto.setEventDate(eventDate);
        return dto;
    }
}