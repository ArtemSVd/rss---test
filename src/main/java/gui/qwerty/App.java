package gui.qwerty;

import gui.model.RssLink;
import gui.test.RssLinkTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;


import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import java.util.Scanner;


public class App {

    Elements elements = new Elements();
    public static void main(String[] args) throws Exception {
        RssLinkTest rssLinkTest = new RssLinkTest();
       App app = new App();
        app.htmlParse();
       for(int i = 1; i<app.elements.size();i++){
            rssLinkTest.recordsAdd(new RssLink(app.elements.get(i).text(),app.elements.get(i).attr("href")));
        }
       //rssLinkTest.recordsRead();



    }
    private static String getHtmlContent(){
        String content = null;
        URLConnection connection = null;
        try {
            connection =  new URL("https://yandex.ru/news/export").openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return content;
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



