package com.aggregator.dao;

import com.aggregator.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        try(Session session = sessionFactory.openSession()){
            session.save(user);
        }
    }

    @Override
    public User getUserById(long id) {
        try(Session session = sessionFactory.openSession()){
            return session.get(User.class, id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = sessionFactory.openSession()){
            @SuppressWarnings("unchecked")
            List<User> users = session.createQuery("from User ").list();
            return users;
        }
    }

    @Override
    public void deleteUser(long id) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            user.setSelectedCategories(null);
            user = (User) session.merge(user);
            session.delete(user);

            transaction.commit();
        }
    }

    @Override
    public void updateUser(User user) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
    }
    @Override
    public User getUserByLogin(String login) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            User user = (User) session.createQuery("from User where login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
            transaction.commit();
             return user;
        } catch (Exception e){
            return null;
        }
    }
}
