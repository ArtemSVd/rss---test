package com.service;

import com.dao.NewsDAO;
import com.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class NewsServiceImpl implements NewsService {
    private final NewsDAO newsDAO;

    @Autowired
    public NewsServiceImpl(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @Override
    public void add(Set<News> newsSet) {
        for(News news : newsSet) {
            newsDAO.addNews(news);
        }
    }

    @Override
    public void update(News news) {
        newsDAO.updateNews(news);
    }

    @Override
    public News getById(int newsId) {
        return newsDAO.getNewsyById(newsId);
    }

    @Override
    public List<News> all() {
        return newsDAO.getAllNews();
    }

    @Override
    public void delete(News news) {
        newsDAO.deleteNews(news);
    }

    @Override
    public List<News> getSortedNewsBySelectedCategories(Set<Integer> selectedCategories) {
        return newsDAO.getSortedNewsBySelectedCategories(selectedCategories);
    }

    @Override
    public List<News> getSortedNewsByAllCategories() {
        return newsDAO.getSortedNewsByAllCategories();
    }
}
