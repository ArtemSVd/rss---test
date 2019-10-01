package main;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Themes {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Map<String, URL> urlMap = new HashMap<>();

        urlMap.put("auto",new URL("https://news.yandex.ru/auto.rss"));
        urlMap.put("auto_racing",new URL("https://news.yandex.ru/auto_racing.rss"));
        urlMap.put("army",new URL("https://news.yandex.ru/army.rss"));
        urlMap.put("basketball",new URL("https://news.yandex.ru/basketball.rss"));
        urlMap.put("world",new URL("https://news.yandex.ru/world.rss"));
        urlMap.put("daily",new URL("https://news.yandex.ru/daily.rss"));
        urlMap.put("volleyball",new URL("https://news.yandex.ru/volleyball.rss"));
        urlMap.put("gadgets",new URL("https://news.yandex.ru/gadgets.rss"));
        urlMap.put("index",new URL("https://news.yandex.ru/index.rss"));
        urlMap.put("martial_arts",new URL("https://news.yandex.ru/martial_arts.rss"));
        urlMap.put("communal",new URL("https://news.yandex.ru/communal.rss"));
        urlMap.put("health",new URL("https://news.yandex.ru/health.rss"));
        urlMap.put("games",new URL("https://news.yandex.ru/games.rss"));

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("newsLink")));
        objectOutputStream.writeObject(urlMap);

    }
}
