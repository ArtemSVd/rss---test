package com.dao;

import com.model.Category;
import com.config.HibernateUtils;
import com.model.News;
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
            Category category = session.get(Category.class, categoryId);
            category.getNewsSet();
            for(News n: category.getNewsSet()){
                n.getCategory();
            }
            return category;
        }
    }

    @Override
    public List<Category> getAllCategories()   {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            @SuppressWarnings("unchecked")
            List<Category> category = session.createQuery("from Category ").list();
            return category;
        }
    }

    @Override
    public void deleteCategory(int id)   {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            Category category = session.get(Category.class, id);
            category.getNewsSet().clear();
            category = (Category) session.merge(category);
            session.delete(category);

            transaction.commit();
        }
    }

}
