package com;

import com.dao.CategoryDAO;
import com.dao.CategoryDAOImpl;
import com.dao.NewsDAO;
import com.dao.NewsDAOImpl;
import com.model.Category;
import com.model.News;
import com.parsers.HtmlParser;
import com.parsers.XmlParser;

import java.util.Date;
import java.util.Map;
import java.util.Set;


public class App {


    public static void main(String[] args) throws Exception {

        HtmlParser htmlParser = new HtmlParser();
        CategoryDAO dao = new CategoryDAOImpl();
        Map<String,String> categoryMap = htmlParser.getRssLinks();

        for(Map.Entry<String,String> m : categoryMap.entrySet()){
            String topic = m.getKey();
            String url = m.getValue();
            if(topic.equals("RSS")) continue;
            dao.addCategory(new Category(topic,url));
        }
        System.out.println("Все ссылки на ленты загрузил");
        XmlParser xmlParser = new XmlParser();
        NewsDAO newsDAO = new NewsDAOImpl();
        for(Category category : dao.getAllCategories()){
            category.setLastUpdate(new Date());
            dao.updateCategory(category);
            Set<News> newsByCategory = xmlParser.getNewsByCategory(category,new Date());
            for(News n : newsByCategory){
                newsDAO.addNews(n);
            }
        }
        //---------------------------------------------------------------------------------

//        List<News> sortedNews = new NewsDAOImpl().getSortedNewsBySelectedCategories(new HashSet<>());
//        for(News news : sortedNews){
//            System.out.println(news.getPubDate()+" : "+news.getTitle());
//        }


    }
}



