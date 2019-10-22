package com.aggregator.service;

import com.aggregator.dao.UserDAO;
import com.aggregator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Сервисный класс, предназначенный для работы с пользователями.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * DAO для работы с пользователями.
     */
    private UserDAO dao;

    @Autowired
    public UserServiceImpl(final UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public void add(final User user) {
        dao.addUser(user);
    }

    @Override
    public void delete(final long id) {
        dao.deleteUser(id);
    }

    @Override
    public void update(final User user) {
        dao.updateUser(user);
    }

    @Override
    public User getByUsername(final String username) {
        return dao.getUserByUsername(username);
    }

    /**
     * Метод предназначен для нахождения записи о
     * текущем авторизованном пользователе.
     * @return пользователь на основании его имени
     */
    public User getAuthorizedUser() {
        return getByUsername(getCurrentUsername());
    }

    /**
     * Метод предназначен для получения имени
     * текущего авторизованного пользователя.
     * @return строка, содержащая имя
     */
    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        return auth.getName();
    }

}
