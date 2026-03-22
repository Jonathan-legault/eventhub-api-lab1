package com.eventhub.eventhubapi.repository;

import com.eventhub.eventhubapi.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for Registration entity.
 * Provides CRUD operations and custom queries for retrieving registrations.
 */
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    /**
     * Finds all registrations for a specific user.
     *
     * @param userId user ID
     * @return list of registrations for the user
     */
    List<Registration> findByUser_Id(Long userId);

    /**
     * Finds registrations within a date range.
     *
     * @param start start date-time
     * @param end end date-time
     * @return list of registrations in the given range
     */
    List<Registration> findByRegistrationDateBetween(LocalDateTime start, LocalDateTime end);
}