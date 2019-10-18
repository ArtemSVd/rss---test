package com.aggregator.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class HtmlParser {
    private final String RSS_SOURCE = "https://yandex.ru/news/export";
    private final String HREF_ATTR = "href";
    private final String TAG_ANCHOR = "a";
    private Elements htmlParse() {
        Elements elements = new Elements();
        try {
            Document doc = Jsoup.connect(RSS_SOURCE).get();
            Elements elementsByClass = doc.getElementsByClass("link link_theme_normal i-bem");

            for (Element byClass : elementsByClass) {
                Elements elementsByTag = byClass.getElementsByTag(TAG_ANCHOR);
                elements.addAll(elementsByTag);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elements;
    }
    public Map<String,String> getRssLinks(){
        Map<String,String> map = new LinkedHashMap<>();
        for(Element element : htmlParse()) {
            String category = element.text();
            String url = element.attr(HREF_ATTR);
            map.put(category,url);
        }
        return map;
    }
}
