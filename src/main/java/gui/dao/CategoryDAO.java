package gui.dao;

import gui.entity.Category;

import java.sql.SQLException;
import java.util.Collection;

public interface CategoryDAO {
     void addCategory(Category category) throws SQLException;
     void updateCategory(Category category) throws SQLException;
     Category getCategoryById(int categoryId) throws SQLException;
     Collection getAllCategories() throws SQLException;
     void deleteCategory(Category category) throws SQLException;
     Collection getCategoryByTopic(String topic) throws SQLException;
     //Collection getAllNewsByCategory(Route route) throws SQLException;
}
