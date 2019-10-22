package com.aggregator.controller;

import com.aggregator.model.Category;
import com.aggregator.model.Selector;
import com.aggregator.model.User;
import com.aggregator.parsers.XmlParser;
import com.aggregator.service.CategoryServiceImpl;
import com.aggregator.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Контроллер для отображения страницы category.jsp.
 */
@Controller
@SessionAttributes("selector")
public class CategoryController {
    /**
     * Сервис для работы с категориями.
     */
    private CategoryServiceImpl service;
    /**
     * Обработчик RSS - лент.
     */
    private XmlParser parser;
    /**
     * Сервис для работы с пользователями.
     */
    private UserServiceImpl userService;

    @Autowired
    public CategoryController(CategoryServiceImpl service, XmlParser parser, UserServiceImpl userService) {
        this.service = service;
        this.parser = parser;
        this.userService = userService;
    }

    /**
     * Первоначальная инициализация селектора
     * (объект для работы с выбранными категориями).
     * @return объект класса Selector
     */
    @ModelAttribute("selector")
    public Selector initSelector(){
        return new Selector();
    }

    /**
     * Метод для получения текущего
     * авторизованного пользователя и
     * считывание в селектор выбранных
     * пользователем категорий.
     * @return возвращает представление
     * страницы category.jsp
     */
    @RequestMapping(value = "/currentUser", method = RequestMethod.POST)
    public ModelAndView getCurrentUser() {
        User authorizedUser = userService.getAuthorizedUser();

        ModelAndView modelAndView = new ModelAndView();

        Set<Long> idSelectedCategories = new HashSet<>();
        for(Category category : authorizedUser.getSelectedCategories()) {
            idSelectedCategories.add(category.getId());
        }

        modelAndView.addObject("selector", new Selector(idSelectedCategories));
        modelAndView.setViewName("redirect:/category");

        return modelAndView;
    }

    /**
     * Метод для отображения списка категорий.
     * @param selector содержит выбранные
     *                 пользователем категории
     * @return возвращает представление
     * страницы category.jsp
     */
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ModelAndView allCategory(@ModelAttribute("selector") Selector selector) {
        ModelAndView modelAndView = new ModelAndView();

        if(userService.getAuthorizedUser() == null) {
            modelAndView.addObject("anon", true);
        } else {
            modelAndView.addObject("username", userService.getAuthorizedUser().getUsername());
        }

        modelAndView.setViewName("category");

        List<Category> categories = service.all();

        modelAndView.addObject("categories", categories);
        modelAndView.addObject("selector", selector);
        modelAndView.addObject("category", new Category());

        return modelAndView;
    }

    /**
     * Метод для сохранения выбранных категорий в сессии
     * и их сохранение для пользователя в случае, если он
     * авторизован.
     * @param selector содержит выбранные категории
     * @return перенаправляет на страницу с новостями
     */
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public ModelAndView selectCategories( @ModelAttribute("selector") final Selector selector) {
       ModelAndView modelAndView = new ModelAndView();

       modelAndView.addObject("selector", selector);

       User user = userService.getAuthorizedUser();
        if(user != null) {
            user.getSelectedCategories().clear();

            for (long id : selector.getSelectedCategories()) {
                user.getSelectedCategories().add(service.getById(id));
            }

            userService.update(user);
        }
       modelAndView.setViewName("redirect:news");
       return modelAndView;
    }

    /**
     * Метод для добавления новых категорий
     * с учетом их корректности.
     * @param category объект, полученный
     *                 из POST - запроса
     * @return представление category.jsp
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public  ModelAndView addCategory(
            @ModelAttribute("category") final Category category) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:category");
        if (parser.isCorrectRss(category) && category.getUrl().length() != 0)
            service.add(category);
        else
            modelAndView.addObject("incorrectRss",true);

        return modelAndView;
    }

    /**
     * Метод для удаления категорий.
     * @param selector содержит набор выбранных
     *                 для удаления категорий
     * @return представление category.jsp
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteCategory(
            @ModelAttribute("selector") final Selector selector) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/category");

        for(long catId : selector.getSelectedCategories())
            service.delete(catId);

        modelAndView.addObject("selector", new Selector());

        return  modelAndView;
    }
}
