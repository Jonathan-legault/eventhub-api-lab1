package com.eventhub.eventhubapi.service;

import com.eventhub.eventhubapi.dto.CategoryDTO;

import java.util.List;

/*
 * Service interface for category-related operations.
 * Defines the actions that can be performed on categories.
 *
 * The implementation of these methods is provided
 * in CategoryServiceImpl.
 */
public interface CategoryService {

    /*
     * Returns all categories available in the system.
     */
    List<CategoryDTO> getAllCategories();

    /*
     * Creates a new category.
     */
    CategoryDTO createCategory(CategoryDTO dto);
}