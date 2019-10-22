package com.aggregator.controller;

import com.aggregator.model.News;
import com.aggregator.model.Page;
import com.aggregator.model.Selector;
import com.aggregator.service.NewsServiceImpl;
import com.aggregator.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

/**
 * Контроллер для отображение страницы news.jsp,
 * содержащей новостные ленты.
 */
@Controller
@SessionAttributes(value = {"selector", "page" })
public class NewsController {
    /**
     * Сервис для работы с новостями.
     */
    private final NewsServiceImpl newsService;
    /**
     * Сервис для работы с пользователями.
     */
    private final UserServiceImpl userService;

    @Autowired
    public NewsController(final NewsServiceImpl newsService,
                          final UserServiceImpl userService) {
        this.newsService = newsService;
        this.userService = userService;
    }

    /**
     * Первоначальная инициализация селектора
     * (объект для работы с выбранными категориями).
     * @return объект класса Selector
     */
    @ModelAttribute("selector")
    public Selector initSelector() {
        return new Selector();
    }

    /**
     * Первоначальная инициализация объекта
     * для отображения страниц
     * @return новый объект Page
     */
    @ModelAttribute("page")
    public Page initPage() {
        return new Page();
    }

    /**
     * Метод для обработки GET - запроса по адресам /news, /.
     * Отображает страницу news.jsp.
     * Содержит новости по выбранным пользователем категориям.
     * @param selector выбранные категории
     * @return модель и представление
     */
    @RequestMapping(value = {"/news", "/" }, method = RequestMethod.GET)
    public final ModelAndView allNews(
            @ModelAttribute(name = "selector") final Selector selector,
            @ModelAttribute(name = "page") Page page) {

        ModelAndView modelAndView = new ModelAndView();

        if (userService.getAuthorizedUser() == null) {
            modelAndView.addObject("anon", true);
        } else {
            modelAndView.addObject("username",
                    userService.getAuthorizedUser().getUsername());
        }

        modelAndView.setViewName("news");

        List<News> newsList;
        int lastPage;
        Set<Long> selectedCategories = selector.getSelectedCategories();
        if (selectedCategories.size() != 0) {
            newsList = newsService.
                    getSortedNewsBySelectedCategories(selectedCategories, page.getNumPage());
            lastPage = newsService.lastPaginatedNews(selectedCategories);
        } else {
            newsList = newsService.getSortedNewsByAllCategories(page.getNumPage());
            lastPage = newsService.lastPaginatedNews(null);
        }

        page.setLastPage(lastPage);

        modelAndView.addObject("page", page);
        modelAndView.addObject("newsList", newsList);
        return modelAndView;
    }

    /**
     * Вызов данного метода обнуляет счетчик страниц.
     * @return модель и представление ведет на category.jsp
     */
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public final ModelAndView resetPageCount(@ModelAttribute("page") Page page) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("page", new Page());

        modelAndView.setViewName("redirect:category");

        return modelAndView;
    }

    /**
     * Метод для перехода на следующую страницу с новостями.
     * @return модель и представление возвращает на news.jsp
     */
    @RequestMapping(value = "/next", method = RequestMethod.GET)
    public final ModelAndView nextPage(@ModelAttribute("page") Page page) {

        if (page.getNumPage() + 1 < page.getLastPage()) {
            page.setNumPage(page.getNumPage() + 1);
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("page", page);

        modelAndView.setViewName("redirect:news");

        return modelAndView;
    }

    /**
     * Метод для перехода на предыдущую страницу с новостями.
     * @return модель и представление возвращает на news.jsp
     */
    @RequestMapping(value = "/prev", method = RequestMethod.GET)
    public final ModelAndView prevPage(@ModelAttribute("page") Page page) {

        if (page.getNumPage() - 1 >= 0) {
            page.setNumPage(page.getNumPage() - 1);
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("page", page);

        modelAndView.setViewName("redirect:news");

        return modelAndView;
    }
}
