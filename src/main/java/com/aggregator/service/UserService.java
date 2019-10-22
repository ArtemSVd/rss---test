package com.aggregator.service;

import com.aggregator.model.User;

/**
 * Сервисный интерфейс для работы с пользователями.
 */
public interface UserService {
    /**
     * Метод для сохранения пользователей.
     * @param user сохраняемый объект
     */
    void add(User user);

    /**
     * Метод для удаления пользователей.
     * @param id идентификатор удаляемого объекта
     */
    void delete(long id);

    /**
     * Метод для обновления пользовательских данных.
     * @param user обновляемый объект
     */
    void update(User user);

    /**
     * Метод для получения пользователя по его имени.
     * @param username имя пользователя
     * @return уникальный объект, исходя из его имени
     */
    User getByUsername(String username);
}
