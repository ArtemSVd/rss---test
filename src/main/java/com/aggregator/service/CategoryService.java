package com.aggregator.service;

import com.aggregator.model.Category;

import java.util.List;
import java.util.Set;

/**
 * Сервисный интерфейс для работы с новостными категориями.
 */
public interface CategoryService {
    /**
     * Метод для сохранения категории.
     * @param category сохраняемый объект
     */
    void add(Category category);

    /**
     * Метод для обновления категории.
     * @param category обновляемый объект
     */
    void update(Category category);

    /**
     * Метод для получения категории по идентификатору.
     * @param id уникальный идентификатор
     * @return категория
     */
    Category getById(long id);

    /**
     * Метод для возвращения набора всех категорий.
     * @return список категорий
     */
    List<Category> all();

    /**
     * Метод для удаления категорий.
     * @param id идентификатор удаляемого объекта
     */
    void delete(long id);

    /**
     * Метод для получения уникальных идентификаторов всех категорий.
     * @return возвращает набор идентификаторов
     */
    Set<Long> getIdForAllCategories();
}
