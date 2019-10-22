package com.aggregator.util;

import com.aggregator.model.Category;
import com.aggregator.model.News;
import com.aggregator.parsers.HtmlParser;
import com.aggregator.parsers.XmlParser;
import com.aggregator.service.CategoryServiceImpl;
import com.aggregator.service.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.Set;

/**
 * Класс предназначен для первоначальной настройки базы
 * (сохранения категорий из новостных лент яндекса,
 * скачивание всех новостей по данным категориям).
 */
@Service
public class Parse {
    /**
     * Сервисный объект предназначен для работы с категориями.
     */
    private CategoryServiceImpl categoryService;
    /**
     * Сервисный объект предназначенный для работы с новостями.
     */
    private NewsServiceImpl newsService;

    /**
     * Бины автоматически встраиваеются spring`ом.
     * @param categoryService сервис для категорий
     * @param newsService сервис для новостей
     */
    @Autowired
    public Parse(final CategoryServiceImpl categoryService,
                 final NewsServiceImpl newsService) {
        this.categoryService = categoryService;
        this.newsService = newsService;
    }

    /**
     * Метод скачивает RSS - ленты яндекса
     * и новости по ним.
     * @throws ParseException выбрасывается при ошибках
     * в обработке html - содержимого
     * @throws XMLStreamException выбрасывается при ошибках
     * в обработке XML - содержимого
     * @throws IOException другие ошибки ввода/вывода
     */
    public  void uploadContent()
            throws ParseException, XMLStreamException, IOException {

        HtmlParser htmlParser = new HtmlParser();

        Map<String, String> rssLinks = htmlParser.getRssLinks();

        for (Map.Entry<String, String> n : rssLinks.entrySet()) {

            if (!n.getKey().equals("RSS")) {
                Category category = new Category(n.getKey(), n.getValue());
                categoryService.add(category);
            }
        }

        XmlParser xmlParser = new XmlParser();

        for (Category category : categoryService.all()) {
            Set<News> newsByCategory = xmlParser.getNewsByCategory(category);
            newsService.add(newsByCategory);
        }
    }
}
