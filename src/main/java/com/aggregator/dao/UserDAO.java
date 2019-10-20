package com.aggregator.dao;

import com.aggregator.model.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getUserById(long id);
    List<User> getAllUsers();
    void deleteUser(long id);
    void updateUser(User user);
    User getUserByLogin(String login);
}
