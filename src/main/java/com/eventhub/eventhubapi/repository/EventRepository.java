package com.eventhub.eventhubapi.repository;

import com.eventhub.eventhubapi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Event entity.
 * Provides CRUD operations and custom query methods for filtering,
 * searching, and reporting event-related data.
 */
public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Finds events by category name, ignoring case.
     *
     * @param categoryName category name
     * @return list of matching events
     */
    List<Event> findByCategory_NameIgnoreCase(String categoryName);

    /**
     * Finds events whose ticket price falls within the given range.
     *
     * @param min minimum ticket price
     * @param max maximum ticket price
     * @return list of matching events
     */
    List<Event> findByTicketPriceBetween(BigDecimal min, BigDecimal max);

    /**
     * Finds all active events.
     *
     * @return list of active events
     */
    List<Event> findByActiveTrue();

    /**
     * Finds events occurring within the specified date range.
     *
     * @param start start date-time
     * @param end end date-time
     * @return list of matching events
     */
    List<Event> findByEventDateBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Checks if an event already exists with the same name and date.
     *
     * @param name event name
     * @param eventDate event date-time
     * @return true if the event exists, false otherwise
     */
    boolean existsByNameIgnoreCaseAndEventDate(String name, LocalDateTime eventDate);

    /**
     * Finds an event by name and date, ignoring case in the name.
     *
     * @param name event name
     * @param eventDate event date-time
     * @return optional matching event
     */
    Optional<Event> findByNameIgnoreCaseAndEventDate(String name, LocalDateTime eventDate);

    /**
     * Searches events by keyword in the event name or description.
     *
     * @param keyword search keyword
     * @return list of matching events
     */
    @Query("""
           SELECT e
           FROM Event e
           WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
              OR LOWER(e.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
           """)
    List<Event> searchByKeyword(String keyword);

    /**
     * Returns the number of registration items recorded for each event.
     *
     * @return list of rows containing event name and registration count
     */
    @Query("""
           SELECT e.name, COUNT(ri.id)
           FROM RegistrationItem ri
           JOIN ri.event e
           GROUP BY e.id, e.name
           """)
    List<Object[]> countRegistrationsPerEvent();

    /**
     * Returns the total number of tickets sold for each event.
     *
     * @return list of rows containing event name and total tickets sold
     */
    @Query("""
           SELECT e.name, COALESCE(SUM(ri.quantity), 0)
           FROM RegistrationItem ri
           JOIN ri.event e
           GROUP BY e.id, e.name
           """)
    List<Object[]> totalTicketsSoldPerEvent();
}