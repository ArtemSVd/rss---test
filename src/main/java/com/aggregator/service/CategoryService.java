package com.aggregator.service;

import com.aggregator.model.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void add(Category category);
    void update(Category category);
    Category getById(long categoryId);
    List<Category> all();
    void delete(long id);
    Set<Long> getIdForAllCategories();
}
