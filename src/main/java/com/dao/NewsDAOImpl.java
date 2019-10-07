package com.dao;

import com.entity.Category;
import com.entity.News;
import com.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;

import java.util.*;

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
    public Set<News> getNewsyById(int newsId) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            List<News> list = session.createQuery("from News WHERE category=" + newsId).list();

            return new HashSet<News>(list);
        }
    }

    @Override
    public Set<News> getAllNews() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            List<News> newsList = session.createQuery("from News ").list();
            Set<News> set = new HashSet(newsList);
            return set;
        }
    }

    @Override
    public void deleteNews(News news) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            session.delete(session.merge(news));
        }
    }

    @Override
    public Collection getNewsByCategory(Category category) {
        return null;
    }

    public List<News> getSortedNews() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            ArrayList<Integer> categoryList  = new ArrayList<>();
            categoryList.add(10);
            categoryList.add(55);
            categoryList.add(48);
            DetachedCriteria criteria = DetachedCriteria.forClass(News.class);
            criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
            List<News> newsList = session.createQuery("select distinct n from News n" +
                    " where n.category.categoryId IN (:cat) group by n.newsId,n.title  ORDER BY n.date desc")
                    .setParameterList("cat",categoryList)
                    .setFirstResult(0)
                    .setMaxResults(40)
                    .list();


            List<String> list = session.createQuery("select distinct title from News n where n.category.categoryId IN (:cat) ")
                    .setParameterList("cat",categoryList)
                    .setFirstResult(1)
                    .setMaxResults(20).list();
            for(String s : list){
                System.out.println(s);
            }

            return newsList;
        }
    }
}
