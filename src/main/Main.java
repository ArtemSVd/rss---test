package main;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;


public class Main {
    public Set<News> getNews() throws IOException, XMLStreamException {
        Set<News> newsList = new HashSet<>();

        URL url = new URL("https://news.yandex.ru/sport.rss");
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
