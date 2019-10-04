package gui.dao;

import gui.entity.Category;
import gui.entity.News;

import java.sql.SQLException;
import java.util.Collection;

public interface NewsDAO {
    void addNews(News news) throws SQLException;
    void updateNews(News news) throws SQLException;
    News getNewsyById(int newsId) throws SQLException;
    Collection getAllNews() throws SQLException;
    void deleteNews(News news) throws SQLException;
    Collection getNewsByCategory(Category category) throws SQLException;
    //Collection getAllNewsByCategory(Route route) throws SQLException;
}
