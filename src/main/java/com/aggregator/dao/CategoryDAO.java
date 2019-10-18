package com.aggregator.dao;

import com.aggregator.model.Category;

import java.util.List;
import java.util.Set;

public interface CategoryDAO {
     void addCategory(Category category);
     void updateCategory(Category category);
     Category getCategoryById(long id);
     List<Category> getAllCategories();
     void deleteCategory(long id);
     Set<Long> getIdForAllCategories();
}
