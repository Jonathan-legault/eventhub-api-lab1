package com.eventhub.eventhubapi.repository;

import com.eventhub.eventhubapi.model.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAll();

    Category save(Category category);
}