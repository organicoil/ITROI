package ua.nure.publisher.parser.stax;

import static ua.nure.publisher.constants.ValueConstants.CATEGORY_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.DESCRIPTION_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINE_QUALIFIED_NAME;
import static ua.nure.publisher.constants.ValueConstants.PER_MONTH_PUBLISH_COUNT_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.PRICE_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.TITLE_ATTRIBUTE;

import org.apache.log4j.Logger;
import ua.nure.publisher.entity.Category;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.entity.Magazines;
import ua.nure.publisher.parser.MagazinesUnmarshaller;
import ua.nure.publisher.parser.sax.SaxMagazinesUnmarshallerImpl;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

public class StaxMagazinesUnmarshallerImpl implements MagazinesUnmarshaller {

    private static final Logger LOG = Logger.getLogger(SaxMagazinesUnmarshallerImpl.class);

    private boolean bTitle = false;
    private boolean bDescription = false;
    private boolean bPrice = false;
    private boolean bPerMonthPublishCount = false;
    private boolean bCategory = false;

    private Magazines magazines = new Magazines();
    private Magazine currentMagazine;

    @Override
    public Magazines unmarshal(String filePath) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(filePath));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                handleEvent(event);
            }
            return magazines;
        } catch (FileNotFoundException | XMLStreamException e) {
            LOG.error("Failed to unmarshal magazines", e);
            return new Magazines();
        }
    }

    private void handleEvent(XMLEvent event) {
        switch (event.getEventType()) {
            case XMLStreamConstants.START_ELEMENT: {
                startElement(event);
                break;
            }
            case XMLStreamConstants.CHARACTERS: {
                elements(event);
                break;
            }
            case XMLStreamConstants.END_ELEMENT: {
                endElement(event);
                break;
            }
        }
    }

    private void startElement(XMLEvent event) {
        StartElement startElement = event.asStartElement();
        String qName = startElement.getName().getLocalPart();
        if (qName.equalsIgnoreCase(MAGAZINE_QUALIFIED_NAME)) {
            currentMagazine = new Magazine();
            Iterator<Attribute> attributes = startElement.getAttributes();
            String id = attributes.next().getValue();
            currentMagazine.setId(Integer.parseInt(id));
        } else if (qName.equalsIgnoreCase(TITLE_ATTRIBUTE)) {
            bTitle = true;
        } else if (qName.equalsIgnoreCase(DESCRIPTION_ATTRIBUTE)) {
            bDescription = true;
        } else if (qName.equalsIgnoreCase(PRICE_ATTRIBUTE)) {
            bPrice = true;
        } else if (qName.equalsIgnoreCase(PER_MONTH_PUBLISH_COUNT_ATTRIBUTE)) {
            bPerMonthPublishCount = true;
        } else if (qName.equalsIgnoreCase(CATEGORY_ATTRIBUTE)) {
            bCategory = true;
        }
    }

    private void elements(XMLEvent event) {
        Characters characters = event.asCharacters();
        if (bTitle) {
            currentMagazine.setTitle(characters.getData());
            bTitle = false;
        }
        if (bDescription) {
            currentMagazine.setDescription(characters.getData());
            bDescription = false;
        }
        if (bPrice) {
            currentMagazine.setPrice(Double.parseDouble(characters.getData()));
            bPrice = false;
        }
        if (bPerMonthPublishCount) {
            currentMagazine.setPerMonthPublishCount(Integer.parseInt(characters.getData()));
            bPerMonthPublishCount = false;
        }
        if (bCategory) {
            currentMagazine.setCategory(Category.fromValue(characters.getData()));
            bCategory = false;
        }
    }

    private void endElement(XMLEvent event) {
        EndElement endElement = event.asEndElement();
        String qName = endElement.getName().getLocalPart();
        if (qName.equalsIgnoreCase(MAGAZINE_QUALIFIED_NAME)) {
            magazines.add(currentMagazine);
        }
    }
}
