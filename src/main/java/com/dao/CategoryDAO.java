package com.dao;

import com.model.Category;

import java.util.List;

public interface CategoryDAO {
     void addCategory(Category category);
     void updateCategory(Category category);
     Category getCategoryById(int categoryId);
     List<Category> getAllCategories();
     void deleteCategory(int id);
}
