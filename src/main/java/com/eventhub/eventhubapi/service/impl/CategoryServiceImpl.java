package com.eventhub.eventhubapi.service.impl;

import com.eventhub.eventhubapi.dto.CategoryDTO;
import com.eventhub.eventhubapi.model.Category;
import com.eventhub.eventhubapi.repository.CategoryRepository;
import com.eventhub.eventhubapi.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    private CategoryDTO toDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }

    private Category toEntity(CategoryDTO dto) {
        return new Category(null, dto.getName());
    }

    @Override
    @Cacheable("categories")
    public List<CategoryDTO> getAllCategories() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "categories", allEntries = true)
    public CategoryDTO createCategory(CategoryDTO dto) {
        Category category = toEntity(dto);
        return toDTO(repository.save(category));
    }
}