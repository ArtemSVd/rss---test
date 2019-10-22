package com.aggregator.dao;

import com.aggregator.model.User;

import java.util.List;
/**
 * DAO - интерфейс, предназначенный для работы с пользователями.
 */
public interface UserDAO {
    /**
     * Метод для сохранения пользователей в базе данных.
     * @param user сохраняемый объект
     */
    void addUser(User user);

    /**
     * Метод для получения всех записей в таблице.
     * @return возвращает всех пользователей
     */
    List<User> getAllUsers();

    /**
     * Метод для удаления пользователей из базы.
     * @param id идентификатор удаляемого пользователя
     */
    void deleteUser(long id);

    /**
     * Метод для обновления данных пользователя.
     * @param user обновляемый объект
     */
    void updateUser(User user);

    /**
     * Метод позволяет получить пользователя по его имени.
     * @param username имя пользователя
     * @return объект пользователя
     */
    User getUserByUsername(String username);
}
