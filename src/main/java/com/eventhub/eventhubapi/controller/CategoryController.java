package com.eventhub.eventhubapi.controller;

import com.eventhub.eventhubapi.dto.CategoryDTO;
import com.eventhub.eventhubapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoryDTO> getAll() {
        return service.getAllCategories();
    }

    @PostMapping
    public CategoryDTO create(@Valid @RequestBody CategoryDTO dto) {
        return service.createCategory(dto);
    }
}