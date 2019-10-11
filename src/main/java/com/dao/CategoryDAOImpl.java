package com.dao;

import com.model.Category;
import com.config.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public void addCategory(Category category)   {
       try(Session session = HibernateUtils.getSessionFactory().openSession()) {
           session.save(category);
       }
    }

    @Override
    public void updateCategory(Category category)   {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
        }
    }

    @Override
    public Category getCategoryById(int categoryId)   {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            return session.get(Category.class, categoryId);
        }
    }

    @Override
    public List<Category> getAllCategories()   {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            return session.createQuery("from Category ").list();
        }
    }

    @Override
    public void deleteCategory(Category category)   {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(session.merge(category));
            transaction.commit();
        }
    }

}
