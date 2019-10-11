package com.dao;

import com.model.News;
import com.config.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class NewsDAOImpl implements NewsDAO{

    @Override
    public void addNews(News news) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            session.save(news);
        }
    }

    @Override
    public void updateNews(News news) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(news);
            transaction.commit();
        }
    }

    @Override
    public News getNewsyById(int newsId) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            return (News) session.createQuery("from News WHERE category=" + newsId)
                    .getSingleResult();
        }
    }

    @Override
    public List<News> getAllNews() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            @SuppressWarnings("unchecked")
            List<News> news = session.createQuery("from News ").list();
            return news;
        }
    }

    @Override
    public void deleteNews(News news) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            session.delete(session.merge(news));
        }
    }

   @Override
    public List<News> getSortedNewsBySelectedCategories(Set<Integer> selectedCategories) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){

            @SuppressWarnings("unchecked")
            List<News> newsList = session.createCriteria(News.class)
                    .setProjection(Projections.distinct(Projections.projectionList()
                            .add(Projections.property("date"),"date")
                            .add(Projections.property("url"),"url")
                            .add(Projections.property("title"),"title")))
                    .setResultTransformer(Transformers.aliasToBean(News.class))
                    .setMaxResults(40)
                    .add(Restrictions.in("category.categoryId",selectedCategories))
                    .addOrder(Order.desc("date"))
                    .list();

            return newsList;
        }
    }

    @Override
    public List<News> getSortedNewsByAllCategories() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {

            @SuppressWarnings("unchecked")
            List<News> newsList = session.createCriteria(News.class)
                    .setProjection(Projections.distinct(Projections.projectionList()
                            .add(Projections.property("date"), "date")
                            .add(Projections.property("url"), "url")
                            .add(Projections.property("title"), "title")))
                    .setResultTransformer(Transformers.aliasToBean(News.class))
                    .setMaxResults(20)
                    .addOrder(Order.desc("date"))
                    .list();

            return newsList;
        }
    }
}
