package com.aggregator.controller;

import com.aggregator.model.News;
import com.aggregator.model.User;
import com.aggregator.service.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Set;

@Controller
@SessionAttributes("user")
public class NewsController {
    private int page = 0;
    private int lastPage = 0;
    private NewsServiceImpl newsService;

    @Autowired
    public NewsController(NewsServiceImpl newsService) {
        this.newsService = newsService;
    }

    @ModelAttribute("user")
    public User initUser(){
        return new User();
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public final ModelAndView allNews(
            @ModelAttribute(name = "user") final User user) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("news");

        List<News> newsList;

        Set<Long> selectedCategories = user.getSelectedCategories();
        if (selectedCategories.size() != 0) {
            newsList = newsService.
                    getSortedNewsBySelectedCategories(selectedCategories, page);
            lastPage = newsService.lastPaginatedNews(selectedCategories);
        }
        else {
            newsList = newsService.getSortedNewsByAllCategories(page);
            lastPage = newsService.lastPaginatedNews(null);
        }
        modelAndView.addObject("newsList", newsList);
        return modelAndView;
    }
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public final ModelAndView resetPageCount(){
        page = 0;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:category");
        return modelAndView;
    }
    @RequestMapping(value ="/next", method = RequestMethod.GET)
    public final ModelAndView nextPage(){
        if(page + 1 < lastPage) {
            page++;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:news");
        return modelAndView;
    }
    @RequestMapping(value ="/prev", method = RequestMethod.GET)
    public final ModelAndView prevPage(){
        if(page - 1 >= 0) {
            page--;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:news");
        return modelAndView;
    }
}
