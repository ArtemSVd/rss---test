package com.controller;

import com.model.CheckboxModel;
import com.model.Category;
import com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class CategoryController {
    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView allCategory(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editRSSpage");
        modelAndView.addObject("selected",new CheckboxModel());

        List<Category> categories = service.all();
        modelAndView.addObject("categories",categories);
        return modelAndView;
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String select(@ModelAttribute CheckboxModel checkboxModel, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("selected", checkboxModel.getSelectedCategories());
        return "redirect:news";
    }
}
