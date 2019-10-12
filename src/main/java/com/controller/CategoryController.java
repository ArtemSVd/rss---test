package com.controller;

import com.model.CheckboxModel;
import com.model.Category;
import com.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;


@Controller
public class CategoryController {

    @Autowired
    private CategoryServiceImpl service;


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView allCategory() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editRSSpage");
        modelAndView.addObject("checkboxModel", new CheckboxModel());

        List<Category> categories = service.all();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String select(
            @ModelAttribute final CheckboxModel checkboxModel,
            final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("checkboxModel",
                checkboxModel.getSelectedCategories());
        return "redirect:news";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public  ModelAndView addCategory(
            @ModelAttribute("category") final Category category) {
        // TODO : проверить корректность введенного юрл (если выбрасывает эксепшн - не сохранять
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit");
        modelAndView.addObject("category", new Category());
        service.add(category);
        return modelAndView;
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteCategory(@PathVariable("id") final int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/edit");
        service.delete(id);
        return  modelAndView;
    }
}
