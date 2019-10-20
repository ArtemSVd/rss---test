package com.aggregator.controller;

import com.aggregator.model.Category;
import com.aggregator.model.User;
import com.aggregator.parsers.XmlParser;
import com.aggregator.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@SessionAttributes("user")
public class CategoryController {
    private CategoryServiceImpl service;

    @Autowired
    public void setService(CategoryServiceImpl service) {
        this.service = service;
    }

    private XmlParser parser;

    @Autowired
    public void setParser(XmlParser parser) {
        this.parser = parser;
    }

    @ModelAttribute("user")
    public User initUser(){
        return new User();
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ModelAndView allCategory() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("category");

        List<Category> categories = service.all();
        modelAndView.addObject("categories", categories);
       // modelAndView.addObject("userInPost", new User());
        modelAndView.addObject("category", new Category());

        return modelAndView;
    }
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public ModelAndView selectCategories( @ModelAttribute("user") final User user) {
       ModelAndView modelAndView = new ModelAndView();

       modelAndView.addObject("user",user);

       modelAndView.setViewName("redirect:news");
       return modelAndView;
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public  ModelAndView addCategory(
            @ModelAttribute("category") final Category category) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:category");
        if (parser.isCorrectRss(category))
            service.add(category);
        else
            modelAndView.addObject("incorrectRss",true);

        return modelAndView;
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteCategory(
            @ModelAttribute("user") final User userFromPost) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/category");

//        for(long i : userFromPost.getSelectedCategories())
//            service.delete(i);

        modelAndView.addObject("user", new User());

        return  modelAndView;
    }
}
