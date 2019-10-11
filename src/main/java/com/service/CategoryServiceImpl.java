package com.service;

import com.dao.CategoryDAO;
import com.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public void add(Category category) {
        categoryDAO.addCategory(category);
    }

    @Override
    public void update(Category category) {
        categoryDAO.updateCategory(category);
    }

    @Override
    public Category getById(int categoryId) {
        return categoryDAO.getCategoryById(categoryId);
    }

    @Override
    public List<Category> all() {
        return categoryDAO.getAllCategories();
    }

    @Override
    public void delete(Category category) {
        categoryDAO.deleteCategory(category);
    }
}
