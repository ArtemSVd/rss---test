package com.aggregator.service;

import com.aggregator.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    User getById(long id);
    List<User> all();
    void delete(long id);
    void update(User user);
    User getByLogin(String login);
}
