package com.eventhub.eventhubapi.repository.impl;

import com.eventhub.eventhubapi.model.Category;
import com.eventhub.eventhubapi.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/*
 * Repository implementation for managing Category data.
 * This version stores categories in memory using a Map
 * instead of a database.
 */
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    // in-memory storage for categories (id -> Category)
    private final Map<Long, Category> categories = new LinkedHashMap<>();

    // simple ID generator used to assign unique category IDs
    private final AtomicLong idGenerator = new AtomicLong(1);

    /*
     * Returns all categories currently stored.
     */
    @Override
    public List<Category> findAll() {

        // convert map values to a list
        return new ArrayList<>(categories.values());
    }

    /*
     * Saves a category to the repository.
     * If the category does not have an ID yet,
     * one will be generated automatically.
     */
    @Override
    public Category save(Category category) {

        // generate a new ID if this is a new category
        if (category.getId() == null) {
            category.setId(idGenerator.getAndIncrement());
        }

        // store the category in the map
        categories.put(category.getId(), category);

        return category;
    }
}