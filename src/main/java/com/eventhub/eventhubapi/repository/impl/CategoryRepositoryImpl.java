package com.eventhub.eventhubapi.repository.impl;

import com.eventhub.eventhubapi.model.Category;
import com.eventhub.eventhubapi.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final Map<Long, Category> categories = new LinkedHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public List<Category> findAll() {
        return new ArrayList<>(categories.values());
    }

    @Override
    public Category save(Category category) {
        if (category.getId() == null) {
            category.setId(idGenerator.getAndIncrement());
        }
        categories.put(category.getId(), category);
        return category;
    }
}