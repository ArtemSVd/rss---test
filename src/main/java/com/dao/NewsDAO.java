package com.dao;

import com.entity.Category;
import com.entity.News;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

public interface NewsDAO {
    void addNews(News news) throws SQLException;
    void updateNews(News news) throws SQLException;
    Set<News> getNewsyById(int newsId) throws SQLException;
    Set<News> getAllNews() throws SQLException;
    void deleteNews(News news) throws SQLException;
    Collection getNewsByCategory(Category category) throws SQLException;
    //Collection getAllNewsByCategory(Route route) throws SQLException;
}
