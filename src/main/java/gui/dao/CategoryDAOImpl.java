package gui.dao;

import gui.entity.Category;
import gui.entity.News;
import gui.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CategoryDAOImpl implements CategoryDAO {
    NewsDAOImpl newsDAO = new NewsDAOImpl();
    @Override
    public void addCategory(Category category) throws SQLException {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
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
            Transaction transaction = session.beginTransaction();
            Category category = session.get(Category.class, categoryId);
            transaction.commit();

            category.setNews(newsDAO.getAllNews());
            return category;
        }
    }

    @Override
    public List<Category> getAllCategories() throws SQLException {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            List<Category> allCategories = session.createQuery("from Category ").list();
            transaction.commit();
            return allCategories;
        }
    }

    @Override
    public void deleteCategory(Category category) throws SQLException {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(session.merge(category));
            System.out.println(category.getCategoryId());
            transaction.commit();
        }
    }

    @Override
    public Collection getCategoryByTopic(String topic) throws SQLException {
        return null;
    }
}
