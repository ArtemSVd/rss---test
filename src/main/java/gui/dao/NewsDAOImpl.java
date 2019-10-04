package gui.dao;

import gui.entity.Category;
import gui.entity.News;
import gui.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.*;

public class NewsDAOImpl implements NewsDAO{

    @Override
    public void addNews(News news) throws SQLException {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(news);
            transaction.commit();
        }
    }

    @Override
    public void updateNews(News news) throws SQLException {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(news);
            transaction.commit();
        }
    }

    @Override
    public News getNewsyById(int newsId) throws SQLException {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            News news = session.load(News.class, newsId);
            transaction.commit();
            return news;
        }
    }

    @Override
    public Set<News> getAllNews() throws SQLException {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            List<News> newsList = session.createQuery("from News ").list();
            Set<News> set = new HashSet(newsList);
            transaction.commit();
            return set;
        }
    }

    @Override
    public void deleteNews(News news) throws SQLException {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(news);
            transaction.commit();
        }
    }

    @Override
    public Collection getNewsByCategory(Category category) throws SQLException {
        return null;
    }
}
