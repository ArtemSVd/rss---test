package com.aggregator.service;

import com.aggregator.model.Category;
import com.aggregator.model.News;

import java.util.List;
import java.util.Set;

public interface NewsService {
    void add(Set<News> newsSet);
    void update(News news);
    News getById(int newsId);
    List<News> all();
    void delete(News news);
    List<News> getSortedNewsBySelectedCategories(Set<Category> selectedCategories, int page) ;
    List<News> getSortedNewsByAllCategories(int page) ;
}
