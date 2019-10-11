package com.dao;

import com.model.News;

import java.util.List;
import java.util.Set;

public interface NewsDAO {
    void addNews(News news);
    void updateNews(News news);
    News getNewsyById(int newsId);
    List<News> getAllNews();
    void deleteNews(News news);
    List<News> getSortedNewsBySelectedCategories(Set<Integer> selectedCategories);
    List<News> getSortedNewsByAllCategories();
}
