package com.dao;

import com.entity.Category;
import com.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;


public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public void addCategory(Category category) throws SQLException {
       try(Session session = HibernateUtils.getSessionFactory().openSession()) {
           session.save(category);
       }
    }

    @Override
    public void updateCategory(Category category) throws SQLException {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
        }
    }

    @Override
    public Category getCategoryById(int categoryId) throws SQLException {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Category category = session.get(Category.class, categoryId);
            return category;
        }
    }

    @Override
    public List<Category> getAllCategories() throws SQLException {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            List<Category> allCategories = session.createQuery("from Category ").list();
            return allCategories;
        }
    }

    @Override
    public void deleteCategory(Category category) throws SQLException {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(session.merge(category));
            transaction.commit();
        }
    }

    @Override
    public Collection getCategoryByTopic(String topic) throws SQLException {
        return null;
    }
}
