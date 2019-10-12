package com.parsers;

import com.TimeUtil;
import com.model.Category;
import com.model.News;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class XmlParser {
    private final String TITLE = "title";
    private final String LINK = "link";
    private final String PUB_DATE = "pubDate";
    private final String ITEM = "item";
    private final String ENCL_SOURCE = "enclosure";


    public Set<News> getNewsByCategory(Category category) throws IOException, XMLStreamException, ParseException {
        Set<News> set = new HashSet<>();

        Date lastUpdate = category.getLastUpdate();

        XMLEventReader eventReader = getEventReader(category);

        News news = new News();
        skipToNextItem(eventReader);
        while(eventReader.hasNext()){
            news.setCategory(category);
            XMLEvent event = eventReader.nextEvent();
            if(event.isStartElement()){
                String localPart = event.asStartElement().getName().getLocalPart();
                switch (localPart){
                    case TITLE:
                        news.setTitle(getCharacterData(eventReader));
                        break;
                    case LINK:
                        news.setUrl(getCharacterData(eventReader));
                        break;
                    case PUB_DATE:
                        String pubDate = getCharacterData(eventReader);
                        Date newsDate = TimeUtil.parseDate(pubDate);
                        if(newsDate.after(lastUpdate)) {
                            news.setDateFromXml(pubDate);
                        }
                        else{
                            skipToNextItem(eventReader);
                            news = new News();
                        }
                        break;
                    case ENCL_SOURCE:
                        //
                        break;
                }
            }
            if(event.isEndElement()){
                String localPart = event.asEndElement().getName().getLocalPart();
                if(ITEM.equals(localPart)){
                    set.add(news);
                    news = new News();
                }
            }
        }
        return set;
    }
    private  String getCharacterData(XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        XMLEvent event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }
    private void skipToNextItem(XMLEventReader eventReader) throws XMLStreamException {
        while(eventReader.hasNext()){
            XMLEvent event = eventReader.nextEvent();
            if(event.isStartElement()){
                String localPart = event.asStartElement().getName().getLocalPart();
                if(ITEM.equals(localPart)) break;
            }
        }
    }
    private XMLEventReader getEventReader(Category category) throws IOException, XMLStreamException {
        URL url = new URL(category.getUrl());
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream in = url.openStream();
        return inputFactory.createXMLEventReader(in);
    }

}
