package com.aggregator.dao;

import com.aggregator.model.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;


@Repository
public class NewsDAOImpl implements NewsDAO {
    private final int PAGE_SIZE = 20;
    private final SessionFactory sessionFactory;

    @Autowired
    public NewsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addNews(News news) {
        try (Session session = sessionFactory.openSession()) {
            session.save(news);
        }
    }

    @Override
    public void updateNews(News news) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(news);
            transaction.commit();
        }
    }

    @Override
    public News getNewsyById(int newsId) {
        try (Session session = sessionFactory.openSession()) {
            return (News) session.createQuery("from News WHERE category=" + newsId)
                    .getSingleResult();
        }
    }

    @Override
    public List<News> getAllNews() {
        try (Session session = sessionFactory.openSession()) {
            @SuppressWarnings("unchecked")
            List<News> news = session.createQuery("from News ").list();
            return news;
        }
    }

    @Override
    public void deleteNews(News news) {
        try (Session session = sessionFactory.openSession()) {
            session.delete(session.merge(news));
        }
    }

    @Override
    public List<News> getSortedNewsBySelectedCategories(Set<Long> selectedCategories, int page) {
        try (Session session = sessionFactory.openSession()) {

            @SuppressWarnings("unchecked")
            List<News> newsList = session.createCriteria(News.class)
                    .setProjection(Projections.distinct(Projections.projectionList()
                            .add(Projections.property("date"), "date")
                            .add(Projections.property("url"), "url")
                            .add(Projections.property("imageUrl"), "imageUrl")
                            .add(Projections.property("title"), "title")))
                    .setResultTransformer(Transformers.aliasToBean(News.class))
                    .add(Restrictions.in("category.id", selectedCategories))
                    .addOrder(Order.desc("date"))
                    .setFirstResult(PAGE_SIZE * page)
                    .setMaxResults(PAGE_SIZE)
                    .list();

            return newsList;
        }
    }

    @Override
    public List<News> getSortedNewsByAllCategories(int page) {
        try (Session session = sessionFactory.openSession()) {

            @SuppressWarnings("unchecked")
            List<News> newsList = session.createCriteria(News.class)
                    .setProjection(Projections.distinct(Projections.projectionList()
                            .add(Projections.property("date"), "date")
                            .add(Projections.property("url"), "url")
                            .add(Projections.property("imageUrl"), "imageUrl")
                            .add(Projections.property("title"), "title")))
                    .setResultTransformer(Transformers.aliasToBean(News.class))
                    .addOrder(Order.desc("date"))
                    .setFirstResult(PAGE_SIZE * page)
                    .setMaxResults(PAGE_SIZE)
                    .list();

            return newsList;
        }
    }

    public int lastPaginatedNews(Set<Long> selectedCategories) {
        try (Session session = sessionFactory.openSession()) {

            @SuppressWarnings("unchecked")
            List<News> newsList = session.createCriteria(News.class)
                    .setProjection(Projections.distinct(Projections.projectionList()
                            .add(Projections.property("date"), "date")
                            .add(Projections.property("url"), "url")
                            .add(Projections.property("imageUrl"), "imageUrl")
                            .add(Projections.property("title"), "title")))
                    .setResultTransformer(Transformers.aliasToBean(News.class))
                    .add(Restrictions.in("category.id", selectedCategories))
                    .addOrder(Order.desc("date"))
                    .list();

            return (int) Math.ceil(newsList.size()/PAGE_SIZE);
        }
    }
}
