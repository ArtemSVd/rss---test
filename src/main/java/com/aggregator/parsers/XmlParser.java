package com.aggregator.parsers;

import com.aggregator.util.TimeUtil;
import com.aggregator.model.Category;
import com.aggregator.model.News;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class XmlParser {
    private final String TITLE = "title";
    private final String LINK = "guid";
    private final String PUB_DATE = "pubDate";
    private final String ITEM = "item";
    private final String ENCL_SOURCE = "enclosure";
    private final QName ATTRIBUTE_URL = new QName("url");

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
                        news.setTitle(eventReader.getElementText());
                        break;
                    case LINK:
                        news.setUrl(eventReader.getElementText());
                        break;
                    case PUB_DATE:
                        String pubDate = eventReader.getElementText();
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
                        Attribute attribute = event.asStartElement().getAttributeByName(ATTRIBUTE_URL);
                        news.setImageUrl(attribute.getValue());
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
    public boolean isCorrectRss(Category category){
        try {
            getNewsByCategory(category);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
