package com.aggregator.service;

import com.aggregator.model.News;

import java.util.List;
import java.util.Set;

/**
 * Сервисный интерфейс для работы с новостями.
 */
public interface NewsService {
    /**
     * Метод для сохранения новостей.
     * @param newsSet набор новостей
     */
    void add(Set<News> newsSet);

    /**
     * Метод для обновления данных о новости.
     * @param news обновляемый объект
     */
    void update(News news);

    /**
     * Метод для удаления новостей.
     * @param news удаляемый объект
     */
    void delete(News news);

    /**
     *  Метод предназначен для получения сортированных по дате новостей.
     * @param selectedCategories список выбранных пользователем категорий
     * @param page номер страницы (размер страницы - 20 новостей)
     * @return сортированный по дате список новостей
     */
    List<News> getSortedNewsBySelectedCategories(
            Set<Long> selectedCategories,
            int page);
    /**
     *  Метод предназначен для получения сортированных по дате новостей
     *  без учета выбранных категорий.
     * @param page номер страницы (размер страницы - 20 новостей)
     * @return сортированный по дате список новостей
     */
    List<News> getSortedNewsByAllCategories(int page);
}
