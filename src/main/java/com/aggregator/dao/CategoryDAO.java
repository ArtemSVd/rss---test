package com.aggregator.dao;

import com.aggregator.model.Category;

import java.util.List;
import java.util.Set;

/**
 * DAO - интерфейс, предназначенный для работы с новостными категориями.
 */
public interface CategoryDAO {
     /**
      * Метод для сохранения категорий в базе данных.
      * @param category объект для сохранения
      */
     void addCategory(Category category);

     /**
      * Метод для обновления категорий в базе данных.
      * @param category обновляемый объект
      */
     void updateCategory(Category category);

     /**
      * Метод для поиска категорий по id.
      * @param id уникальный идентификатор
      * @return возвращает категорию из базы данных
      */
     Category getCategoryById(long id);

     /**
      * Метод для получения всех категорий.
      * @return возвращает List, содержащий все категории
      */
     List<Category> getAllCategories();

     /**
      * Метод для удаления категорий из базы данных.
      * @param id идентификатор удаляемой категории
      */
     void deleteCategory(long id);

     /**
      * Метод предназначен для поиска всех ID.
      * @return возвращает уникальные
      * идентификаторы всех категорий
      */
     Set<Long> getIdForAllCategories();
}
