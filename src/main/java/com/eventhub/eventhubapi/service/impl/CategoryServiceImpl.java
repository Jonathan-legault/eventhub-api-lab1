package com.eventhub.eventhubapi.service.impl;

import com.eventhub.eventhubapi.dto.CategoryDTO;
import com.eventhub.eventhubapi.model.Category;
import com.eventhub.eventhubapi.repository.CategoryRepository;
import com.eventhub.eventhubapi.service.CategoryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Service implementation for category-related business logic.
 * This class connects the controller layer with the repository layer
 * and handles converting between DTOs and model objects.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    // constructor injection of the repository
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    /*
     * Converts a Category entity to a CategoryDTO.
     */
    private CategoryDTO toDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }

    /*
     * Converts a CategoryDTO to a Category entity.
     */
    private Category toEntity(CategoryDTO dto) {
        return new Category(null, dto.getName());
    }

    /*
     * Returns all categories.
     * The result is cached to improve performance.
     */
    @Override
    @Cacheable("categories")
    public List<CategoryDTO> getAllCategories() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /*
     * Creates a new category if it does not already exist.
     * If a category with the same name already exists, return it instead.
     * The categories cache is cleared when a category is added or reused.
     */
    @Override
    @CacheEvict(value = "categories", allEntries = true)
    public CategoryDTO createCategory(CategoryDTO dto) {
        Category existing = repository.findByNameIgnoreCase(dto.getName())
                .orElse(null);

        if (existing != null) {
            return toDTO(existing);
        }

        Category category = toEntity(dto);
        return toDTO(repository.save(category));
    }
}