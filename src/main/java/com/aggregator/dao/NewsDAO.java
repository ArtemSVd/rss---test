package com.aggregator.dao;

import com.aggregator.model.News;

import java.util.List;
import java.util.Set;
/**
 * DAO - интерфейс, предназначенный для работы с новостями.
 */
public interface NewsDAO {
    /**
     * Метод для сохранения новостей в базе данных.
     * @param news сохраняемый объект
     */
    void addNews(News news);

    /**
     * Метод для обновления новостей в базе данных.
     * @param news обновляемый объект
     */
    void updateNews(News news);

    /**
     * Метод предназначен для удаления новостей.
     * @param news удаляемый объект
     */
    void deleteNews(News news);

    /**
     *  Метод предназначен для получения сортированных по дате новостей.
     * @param selectedCategories список выбранных пользователем категорий
     * @param page номер страницы (размер страницы - 20 новостей)
     * @return сортированный по дате список новостей
     */
    List<News> getSortedNewsBySelectedCategories(
            Set<Long> selectedCategories, int page);

    /**
     *  Метод предназначен для получения сортированных по дате новостей
     *  без учета выбранных категорий.
     * @param page номер страницы (размер страницы - 20 новостей)
     * @return сортированный по дате список новостей
     */
    List<News> getSortedNewsByAllCategories(int page);

    /**
     * Метод, исходя из выбранных категорий позволяет
     * получить количество страниц.
     * @param selectedCategories список выбранных пользователем категорий
     * @return возвращает количество страниц в зависимости от того,
     * что максимальный размер страницы - 20 новостей
     */
    int lastPaginatedNews(Set<Long> selectedCategories);

}
