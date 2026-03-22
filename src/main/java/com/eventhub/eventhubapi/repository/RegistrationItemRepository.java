package com.eventhub.eventhubapi.repository;

import com.eventhub.eventhubapi.model.RegistrationItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for RegistrationItem entity.
 * Provides basic CRUD operations.
 */
public interface RegistrationItemRepository extends JpaRepository<RegistrationItem, Long> {
}