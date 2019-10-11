package com.service;

import com.model.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);
    void update(Category category);
    Category getById(int categoryId);
    List<Category> all();
    void delete(Category category);
}
