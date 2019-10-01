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



public class Main {
    public static void main(String[] args) throws IOException, XMLStreamException {
        URL url = new URL("https://news.yandex.ru/games.rss");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // First create a new XMLInputFactory
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();

        // Setup a new eventReader
        InputStream in = url.openStream();
        XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
        // read the XML document
        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            if (event.isStartElement()) {
                String localPart = event.asStartElement().getName().getLocalPart();
                if(localPart.equals("title")) {
                    String title = getCharacterData(event, eventReader);
                    System.out.println(title);
                }
            }

            }
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
