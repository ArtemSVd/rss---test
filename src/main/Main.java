package main;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.net.HttpURLConnection;

import java.net.URL;
import java.text.ParseException;
import java.util.*;


public class Main {
    TreeSet<News> newsList = new TreeSet<>();
    public Set<News> getNews() throws IOException, ClassNotFoundException, XMLStreamException, ParseException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("newsLink")));
        Map<String,URL> newsMap = (HashMap) objectInputStream.readObject();

        getNewsFromThemes(newsMap.get("army"));
        getNewsFromThemes(newsMap.get("health"));
        getNewsFromThemes(newsMap.get("communal"));

        return newsList;
    }
    public Set<News> getNewsFromThemes(URL url) throws IOException, XMLStreamException, ParseException {
        //URL url = new URL("https://news.yandex.ru/football.rss");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();

        InputStream in = url.openStream();
        XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
        News news = null;
        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            if (event.isStartElement()) {
                String localPart = event.asStartElement().getName().getLocalPart();
                if (news != null) {
                    if (localPart.equals("title")) {
                        news.setTitle(getCharacterData(event, eventReader));
                    }
                    if (localPart.equals("link")) {
                        news.setLink(getCharacterData(event, eventReader));
                    }
                    if (localPart.equals("pubDate")) {
                        news.setPubDate(getCharacterData(event, eventReader));
                    }
                    if (news.isNewsReady()) {
                        newsList.add(news);
                    }
                }
                if (localPart.equals("item")) {
                   news = new News();
                }
            }
            }
        return  newsList;
    }
    private static String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }
}
