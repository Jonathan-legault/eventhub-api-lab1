package com.eventhub.eventhubapi.service;

import com.eventhub.eventhubapi.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO createCategory(CategoryDTO dto);
}