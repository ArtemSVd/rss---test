package com.controller;

import com.TimeDiff;
import com.model.Category;
import com.model.News;
import com.parsers.XmlParser;
import com.service.CategoryService;
import com.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Controller
public class NewsController {

    private final NewsService newsService;
    private final CategoryService categoryService;

    @Autowired
    public NewsController(NewsService newsService, CategoryService categoryService) {
        this.newsService = newsService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/news",method = RequestMethod.GET)
    public ModelAndView allNews(@ModelAttribute("selected") Set<Integer> selectedCategories) throws ParseException, XMLStreamException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("news");
        List<News> news;
        if(selectedCategories.size() !=0) {
                addNewNews(selectedCategories);
                news = newsService.getSortedNewsBySelectedCategories(selectedCategories);
            }
        else {
            news = newsService.getSortedNewsByAllCategories();
        }
        modelAndView.addObject("news", news);
        return modelAndView;
    }

    private void addNewNews(Set<Integer> selectedCategories) throws ParseException, XMLStreamException, IOException {
        Date currentDate = new Date();
        XmlParser xmlParser = new XmlParser();
        for(int i : selectedCategories){
            Category category = categoryService.getById(i);
            if(TimeDiff.getDateDiff(currentDate,category.getLastUpdate(),TimeUnit.MINUTES) >=10) {

                category.setLastUpdate(currentDate);
                categoryService.update(category);

                Set<News> newsByCategory = xmlParser.getNewsByCategory(category, currentDate);
                newsService.add(newsByCategory);
            }
        }
    }


}
