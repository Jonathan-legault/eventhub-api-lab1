package com.eventhub.eventhubapi.repository;

import com.eventhub.eventhubapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for Category entity.
 * Provides CRUD operations and custom query methods.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Finds a category by name (case-insensitive).
     *
     * @param name category name
     * @return Optional containing the category if found
     */
    Optional<Category> findByNameIgnoreCase(String name);
}