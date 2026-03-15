package com.eventhub.eventhubapi.controller;

import com.eventhub.eventhubapi.dto.CategoryDTO;
import com.eventhub.eventhubapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * REST controller responsible for handling API requests related to categories.
 * It provides endpoints for retrieving and creating event categories.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    // constructor injection of the CategoryService
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    /*
     * GET /api/categories
     * Returns a list of all categories in the system.
     */
    @GetMapping
    public List<CategoryDTO> getAll() {
        return service.getAllCategories();
    }

    /*
     * POST /api/categories
     * Creates a new category.
     * @Valid ensures the request body follows validation rules in CategoryDTO.
     */
    @PostMapping
    public CategoryDTO create(@Valid @RequestBody CategoryDTO dto) {
        return service.createCategory(dto);
    }
}