package com.eventhub.eventhubapi.config;

import com.eventhub.eventhubapi.dto.CreateEventDTO;
import com.eventhub.eventhubapi.dto.CategoryDTO;
import com.eventhub.eventhubapi.service.CategoryService;
import com.eventhub.eventhubapi.service.EventService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * DataLoader is used to insert sample data when the application starts.
 * It runs automatically because it implements CommandLineRunner.
 * This helps populate the database with some categories and events
 * so the API has data to work with during testing.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final EventService eventService;
    private final CategoryService categoryService;

    // constructor injection for required services
    public DataLoader(EventService eventService, CategoryService categoryService) {
        this.eventService = eventService;
        this.categoryService = categoryService;
    }

    /*
     * This method runs when the application starts.
     * It loads categories first, then events.
     */
    @Override
    public void run(String... args) {
        loadCategories();
        loadEvents();
    }

    /*
     * Creates some default event categories in the database.
     */
    private void loadCategories() {
        categoryService.createCategory(new CategoryDTO(null, "Conference"));
        categoryService.createCategory(new CategoryDTO(null, "Workshop"));
        categoryService.createCategory(new CategoryDTO(null, "Concert"));
        categoryService.createCategory(new CategoryDTO(null, "Business"));
        categoryService.createCategory(new CategoryDTO(null, "Gaming"));
        categoryService.createCategory(new CategoryDTO(null, "Meetup"));
    }

    /*
     * Creates several sample events using the EventService.
     * These events will be available through the API for testing.
     */
    private void loadEvents() {

        // example developer conference
        eventService.createEvent(buildEvent(
                "Spring Boot Conference",
                "Developer conference for Spring Boot.",
                new BigDecimal("150"),
                "Conference",
                true,
                LocalDateTime.of(2026, 6, 1, 10, 0)
        ));

        // practical coding workshop
        eventService.createEvent(buildEvent(
                "Spring Boot Hands-On Workshop",
                "Practical workshop on building REST APIs with Spring Boot.",
                new BigDecimal("75"),
                "Workshop",
                true,
                LocalDateTime.of(2026, 5, 10, 13, 0)
        ));

        // music festival event
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

    /*
     * Helper method used to build an event DTO.
     * This keeps the event creation code cleaner above.
     */
    private CreateEventDTO buildEvent(
            String name,
            String description,
            BigDecimal ticketPrice,
            String category,
            Boolean active,
            LocalDateTime eventDate
    ) {

        // create a DTO object and populate its fields
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