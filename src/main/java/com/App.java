package com;

import com.dao.CategoryDAOImpl;
import com.service.CategoryService;
import com.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class App {

    @Autowired
    static private  CategoryService service = new CategoryServiceImpl(new CategoryDAOImpl());
    public static void main(String[] args) throws Exception {

//
//        HtmlParser htmlParser = new HtmlParser();
//        CategoryDAO dao = new CategoryDAOImpl();
//        Map<String,String> categoryMap = htmlParser.getRssLinks();
//
//        for(Map.Entry<String,String> m : categoryMap.entrySet()){
//            String topic = m.getKey();
//            String url = m.getValue();
//            if(topic.equals("RSS")) continue;
//            dao.addCategory(new Category(topic,url));
//        }
//        System.out.println("Все ссылки на ленты загрузил");
//        XmlParser xmlParser = new XmlParser();
//        NewsDAO newsDAO = new NewsDAOImpl();
//
//        for(Category category : dao.getAllCategories()){
//            Set<News> newsByCategory = xmlParser.getNewsByCategory(category);
//            category.setLastUpdate(new Date());
//            dao.updateCategory(category);
//            for(News n : newsByCategory){
//                newsDAO.addNews(n);
//            }
//        }
        //---------------------------------------------------------------------------------

//        List<News> sortedNews = new NewsDAOImpl().getSortedNewsBySelectedCategories(new HashSet<>());
//        for(News news : sortedNews){
//            System.out.println(news.getPubDate()+" : "+news.getTitle());
//        }


    }
}



