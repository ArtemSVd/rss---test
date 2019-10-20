package com.aggregator.dao;

import com.aggregator.model.Category;
import com.aggregator.model.News;

import java.util.List;
import java.util.Set;

public interface NewsDAO {
    void addNews(News news);
    void updateNews(News news);
    News getNewsyById(int newsId);
    List<News> getAllNews();
    void deleteNews(News news);
    List<News> getSortedNewsBySelectedCategories(Set<Long> selectedCategories, int page);
    List<News> getSortedNewsByAllCategories(int page);
    int lastPaginatedNews(Set<Long> selectedCategories);

}
