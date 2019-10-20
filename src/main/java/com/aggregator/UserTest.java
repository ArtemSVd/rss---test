package com.aggregator;

import com.aggregator.dao.CategoryDAOImpl;
import com.aggregator.dao.UserDAOImpl;
import com.aggregator.model.Category;
import com.aggregator.model.User;
import com.aggregator.service.UserService;
import com.aggregator.service.UserServiceImpl;
import com.aggregator.util.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

public class UserTest {

    private UserService service;

    @Autowired
    public UserTest(UserService service) {
        this.service = service;
    }

    public UserService getService() {
        return service;
    }

    public void setService(UserService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("warning");
        UserTest userTest = new UserTest(new UserServiceImpl(new UserDAOImpl(HibernateUtils.getSessionFactory())));
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl(HibernateUtils.getSessionFactory());

        userTest.getService().add(new User("admin","admin"));
        userTest.getService().add(new User("user","user"));

        for(User user : userTest.getService().all()) {
            logger.info(user.toString());
        }

        Category category1 = new Category("Danilka","Loh");
        categoryDAO.addCategory(category1);

        Category category2 = new Category("Danya","Zasranya");
        categoryDAO.addCategory(category2);

        User byId = userTest.getService().getById(1);
        byId.getSelectedCategories().add(category1);
        byId.getSelectedCategories().add(category2);
        userTest.getService().update(byId);

        for(User user : userTest.getService().all()) {
            logger.info(user.toString());
        }

        userTest.getService().delete(1);
        userTest.getService().delete(2);


        for(Category category : categoryDAO.getAllCategories()){
            logger.info(category.toString());
        }


    }
}
