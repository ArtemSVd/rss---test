package com.aggregator.service;

import com.aggregator.dao.UserDAO;
import com.aggregator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO dao;

    @Autowired
    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public void add(User user) {
        dao.addUser(user);
    }

    @Override
    public User getById(long id) {
        return dao.getUserById(id);
    }

    @Override
    public List<User> all() {
        return dao.getAllUsers();
    }

    @Override
    public void delete(long id) {
        dao.deleteUser(id);
    }

    @Override
    public void update(User user) {
        dao.updateUser(user);
    }

    @Override
    public User getByLogin(String login) {
        return dao.getUserByLogin(login);
    }

}
