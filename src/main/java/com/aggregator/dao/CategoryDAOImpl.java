package com.aggregator.dao;

import com.aggregator.model.Category;
import com.aggregator.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCategory(Category category)   {
       try(Session session = sessionFactory.openSession()) {
           session.save(category);
       }
    }

    @Override
    public void updateCategory(Category category)   {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
        }
    }

    @Override
    public Category getCategoryById(long id)   {
        try(Session session = sessionFactory.openSession()){
            return session.get(Category.class, id);
        }
    }

    @Override
    public List<Category> getAllCategories()   {
        try(Session session = sessionFactory.openSession()){
            @SuppressWarnings("unchecked")
            List<Category> category = session.createQuery("from Category ").list();
            return category;
        }
    }

    @Override
    public void deleteCategory(long id)   {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            Category category = session.get(Category.class, id);
            category.setNewsSet(null);
            category = (Category) session.merge(category);
            session.delete(category);

            transaction.commit();
        }
    }

    @Override
    public Set<Long> getIdForAllCategories() {
        Set<Long> allID = new HashSet<>();
        try(Session session = sessionFactory.openSession()){
            List id = session.createQuery("SELECT id from Category").list();
            allID.addAll(id);
        }
        return allID;
    }

}
