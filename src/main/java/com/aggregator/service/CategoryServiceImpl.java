package com.aggregator.service;

import com.aggregator.dao.CategoryDAO;
import com.aggregator.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
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
    public Category getById(long categoryId) {
        return categoryDAO.getCategoryById(categoryId);
    }

    @Override
    public List<Category> all() {
        return categoryDAO.getAllCategories();
    }

    @Override
    public void delete(long id) {
        categoryDAO.deleteCategory(id);
    }

    @Override
    public Set<Long> getIdForAllCategories() {
        return categoryDAO.getIdForAllCategories();
    }
}
