package com.eventhub.eventhubapi.repository;

import com.eventhub.eventhubapi.model.Category;

import java.util.List;

/*
 * Repository interface for Category data.
 * Defines the basic operations that can be performed
 * on categories such as retrieving and saving them.
 *
 * The actual implementation is provided in CategoryRepositoryImpl.
 */
public interface CategoryRepository {

    /*
     * Returns all categories stored in the repository.
     */
    List<Category> findAll();

    /*
     * Saves a new category or updates an existing one.
     */
    Category save(Category category);
}