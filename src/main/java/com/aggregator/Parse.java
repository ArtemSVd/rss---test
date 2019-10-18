package com.aggregator;

import com.aggregator.dao.CategoryDAOImpl;
import com.aggregator.dao.NewsDAOImpl;
import com.aggregator.model.Category;
import com.aggregator.model.News;
import com.aggregator.parsers.HtmlParser;
import com.aggregator.parsers.XmlParser;
import com.aggregator.service.CategoryServiceImpl;
import com.aggregator.service.NewsService;
import com.aggregator.service.NewsServiceImpl;
import com.aggregator.util.HibernateUtils;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.Set;

public class Parse {
    public static void main(String[] args) throws ParseException, XMLStreamException, IOException {
        HtmlParser htmlParser = new HtmlParser();
        CategoryServiceImpl categoryService = new CategoryServiceImpl(new CategoryDAOImpl(HibernateUtils.getSessionFactory()));
        for(Map.Entry<String,String> n : htmlParser.getRssLinks().entrySet()){
            if(!n.getKey().equals("RSS")){
            Category category = new Category(n.getKey(),n.getValue());
            categoryService.add(category);
            }
        }
        XmlParser xmlParser = new XmlParser();
        NewsDAOImpl newsDAO = new NewsDAOImpl(HibernateUtils.getSessionFactory());

        for(Category category : categoryService.all()){
            Set<News> newsByCategory = xmlParser.getNewsByCategory(category);
            for(News n : newsByCategory){
                newsDAO.addNews(n);
            }
        }

    }
}
