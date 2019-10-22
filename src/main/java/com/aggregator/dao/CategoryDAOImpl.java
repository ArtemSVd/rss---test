package com.aggregator.dao;

import com.aggregator.model.Category;
import com.aggregator.model.User;
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
    private final UserDAO userDAO;

    @Autowired
    public CategoryDAOImpl(SessionFactory sessionFactory, UserDAO userDAO) {
        this.sessionFactory = sessionFactory;
        this.userDAO = userDAO;
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
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();

            Category category = getCategoryById(id);

            for(User user : userDAO.getAllUsers()) {
                if(user.getSelectedCategories().contains(category)) {
                    user.getSelectedCategories().remove(category);
                    userDAO.updateUser(user);
                }
            }

            category.setNewsSet(null);
            category.setUsers(null);
            category = (Category) session.merge(category);
            session.delete(category);

            transaction.commit();
        }
    }

    @Override
    public Set<Long> getIdForAllCategories() {
        Set<Long> idAllCategories = new HashSet<>();
        try(Session session = sessionFactory.openSession()){
            @SuppressWarnings("unchecked")
            List<Long> id = session.createQuery("SELECT id from Category").list();
            idAllCategories.addAll(id);
        }
        return idAllCategories;
    }

}
