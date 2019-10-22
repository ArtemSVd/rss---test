package com.aggregator.service;

import com.aggregator.dao.CategoryDAO;
import com.aggregator.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Сервисный класс, предназначенный для работы с категориями.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    /**
     * DAO - объект, предназначенный для работы с категориями.
     */
    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImpl(final CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public void add(final Category category) {
        categoryDAO.addCategory(category);
    }

    @Override
    public void update(final Category category) {
        categoryDAO.updateCategory(category);
    }

    @Override
    public Category getById(final long id) {
        return categoryDAO.getCategoryById(id);
    }

    @Override
    public List<Category> all() {
        return categoryDAO.getAllCategories();
    }

    @Override
    public void delete(final long id) {
        categoryDAO.deleteCategory(id);
    }

    @Override
    public Set<Long> getIdForAllCategories() {
        return categoryDAO.getIdForAllCategories();
    }
}
