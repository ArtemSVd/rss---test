package com.parsers;

import com.entity.Category;
import com.entity.News;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public class XmlParser {
    private final String TITLE = "title";
    private final String LINK = "link";
    private final String PUB_DATE = "pubDate";
    private final String ITEM = "item";

    public Set<News> getNewsByCategory(Category category) throws IOException, XMLStreamException, ParseException {
        URL url = new URL(category.getUrl());

        Set<News> set = new HashSet<>();
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();

        InputStream in = url.openStream();
        XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

        String localPart = "";
        XMLEvent event;

        while (!ITEM.equals(localPart) && eventReader.hasNext() ) {
            event = eventReader.nextEvent();
            localPart = event.isStartElement() ? event.asStartElement().getName().getLocalPart() : null;
        }

        News news = new News();
        while (eventReader.hasNext()) {
            news.setCategory(category);
            event = eventReader.nextEvent();
            if (event.isStartElement()) {
                localPart = event.asStartElement().getName().getLocalPart();

                switch (localPart) {
                    case TITLE:
                        news.setTitle(getCharacterData(event, eventReader));
                        break;
                    case LINK:
                        news.setUrl(getCharacterData(event, eventReader));
                        break;
                    case PUB_DATE:
                        news.setDateFromXml(getCharacterData(event, eventReader));
                        break;
                    case ITEM:
                        news = new News();
                        break;
                }
            }
            if (news.isReady())
                set.add(news);
        }
        return  set;
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
