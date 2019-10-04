package gui.qwerty;

import gui.dao.CategoryDAO;
import gui.dao.CategoryDAOImpl;
import gui.dao.NewsDAO;
import gui.dao.NewsDAOImpl;
import gui.entity.Category;

import gui.entity.News;
import org.hibernate.LockMode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Set;


public class App {
    Elements elements = new Elements();

    public static void main(String[] args) throws Exception {
        App app = new App();
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        app.htmlParse();
        for (int i = 1; i < app.elements.size(); i++) {
            categoryDAO.addCategory(new Category(app.elements.get(i).text(), app.elements.get(i).attr("href")));
        }
        Main main = new Main();

        CategoryDAOImpl dao = new CategoryDAOImpl();
        NewsDAO newsDAO = new NewsDAOImpl();

//        for(Category category : dao.getAllCategories()){
//            System.out.println(category.getCategoryId());
//            Set<News> set = main.getNewsByCategory(category);
//           for(News news : set) {
//               newsDAO.addNews(news);
//           }
        Category category = dao.getCategoryById(1);
        Set<News> set = main.getNewsByCategory(category);
        for(News news : set) {
               newsDAO.addNews(news);
           }

        // dao.updateCategory(category);
    }

    public  void htmlParse() {
        String title="";
        Document doc;
        try {
            doc = Jsoup.connect("https://yandex.ru/news/export").get();
            title = doc.title();
            Elements elementsByClass = doc.getElementsByClass("link link_theme_normal i-bem");

           for(int i=0;i<elementsByClass.size();i++){
               Elements elementsByTag = elementsByClass.get(i).getElementsByTag("a");
              elements.addAll(elementsByTag);
           }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



