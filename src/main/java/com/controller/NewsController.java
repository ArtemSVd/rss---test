package com.controller;

import com.TimeUtil;
import com.model.Category;
import com.model.News;
import com.parsers.XmlParser;
import com.service.CategoryServiceImpl;
import com.service.NewsServiceImpl;
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

    @Autowired
    private NewsServiceImpl newsService;
    @Autowired
    private CategoryServiceImpl categoryService;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public final ModelAndView allNews(
            @ModelAttribute("checkboxModel") final Set<Integer> selectedCategories)
            throws ParseException, XMLStreamException, IOException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("news");
        List<News> news;
        if (selectedCategories.size() != 0) {
                loadNewsBySelectedCategories(selectedCategories);
                news = newsService.
                        getSortedNewsBySelectedCategories(selectedCategories);
        } else {
            loadNewsByAllCategories();
            news = newsService.getSortedNewsByAllCategories();
        }
        modelAndView.addObject("news", news);
        return modelAndView;
    }


    private void loadNewsBySelectedCategories(final Set<Integer>  selectedCategories)
            throws ParseException, XMLStreamException, IOException {

        final int TEN_MINUTES = 10;
        Date currentDate = new Date();
        XmlParser xmlParser = new XmlParser();
        for (int i : selectedCategories) {
            Category category = categoryService.getById(i);
            Date lastUpdate = category.getLastUpdate();
            long minutesDiff = TimeUtil.getDateDiff(lastUpdate, currentDate, TimeUnit.MINUTES);
            if (minutesDiff >= TEN_MINUTES) {
                Set<News> newsByCategory = xmlParser.getNewsByCategory(category);
                newsService.add(newsByCategory);

                category.setLastUpdate(currentDate);
                categoryService.update(category);
            }
        }
    }
    private void loadNewsByAllCategories() throws ParseException, XMLStreamException, IOException {
        Date currentDate = new Date();
        XmlParser xmlParser = new XmlParser();
        for (Category category : categoryService.all()) {
            if (TimeUtil.getDateDiff(category.getLastUpdate(),currentDate,TimeUnit.MINUTES) >=10) {
                Set<News> newsByCategory = xmlParser.getNewsByCategory(category);
                newsService.add(newsByCategory);

                category.setLastUpdate(currentDate);
                categoryService.update(category);
            }
        }
    }


}
