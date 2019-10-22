package com.aggregator.controller;

import com.aggregator.model.User;
import com.aggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Контроллер предназначен для отображения
 * страницы регистрации пользователей.
 */
@Controller
public class RegistrationController {
    /**
     * Сервис для работы с пользователями.
     */
    private final UserService userService;

    @Autowired
    public RegistrationController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод для обработки GET - запроса.
     * @return возвращает представление
     * страницы регистрации
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.addObject("anon", true);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    /**
     * Метод для обработки POST - запроса
     * проверяет пользователя и сохраняет в базу.
     * @param user регистрируемый пользователь
     * @return возвращает страницу регистрации
     * в случае неудачи или страницу авторизации
     * в случае успеха
     */
    @RequestMapping(name = "/register", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") final User user) {
        User userFromDb = userService.getByUsername(user.getUsername());

        ModelAndView modelAndView = new ModelAndView();

        if (userFromDb != null) {
            modelAndView.addObject(
                    "message",
                    "Пользователь с таким именем уже существует!");
            modelAndView.addObject("anon", true);
            modelAndView.setViewName("registration");
            return modelAndView;
        }

        userService.add(user);

        modelAndView.setViewName("redirect:/login");
        return modelAndView;

    }
}
