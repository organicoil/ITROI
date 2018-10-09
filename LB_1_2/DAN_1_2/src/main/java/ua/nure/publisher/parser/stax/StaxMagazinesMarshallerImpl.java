package ua.nure.publisher.parser.stax;

import static ua.nure.publisher.constants.ValueConstants.CATEGORY_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.DESCRIPTION_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.ID_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_NAMESPACE;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_PREFIX;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_SCHEMA_LOCATION;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINE_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.PER_MONTH_PUBLISH_COUNT_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.PRICE_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.SCHEMA_LOCATION_PARAMETER;
import static ua.nure.publisher.constants.ValueConstants.TITLE_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.UTF_ENCODING;
import static ua.nure.publisher.constants.ValueConstants.UTF_ENCODING_VERSION;
import static ua.nure.publisher.constants.ValueConstants.XSI_NAMESPACE;
import static ua.nure.publisher.constants.ValueConstants.XSI_PREFIX;
import static ua.nure.publisher.parser.stax.util.StaxMarshallingUtils.writeElement;

import org.apache.log4j.Logger;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.entity.Magazines;
import ua.nure.publisher.parser.MagazinesMarshaller;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StaxMagazinesMarshallerImpl implements MagazinesMarshaller {

    private static final Logger LOG = Logger.getLogger(StaxMagazinesMarshallerImpl.class);

    @Override
    public void marshal(Magazines magazines, String filePath) {
        try {
            writeDocument(magazines, filePath);
        } catch (XMLStreamException | IOException e) {
            LOG.error("Failed to marshal magazines", e);
        }
    }

    private XMLStreamWriter createXmlStreamWriter(String filePath) throws XMLStreamException, IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        return xmlOutputFactory.createXMLStreamWriter(fileWriter);
    }

    private void writeDocument(Magazines magazines, String filePath) throws XMLStreamException, IOException {
        XMLStreamWriter xmlStreamWriter = createXmlStreamWriter(filePath);
        xmlStreamWriter.writeStartDocument(UTF_ENCODING, UTF_ENCODING_VERSION);
        writeMagazines(magazines, xmlStreamWriter);
        xmlStreamWriter.writeEndDocument();
        xmlStreamWriter.flush();
        xmlStreamWriter.close();
    }

    private void writeMagazines(Magazines magazines, XMLStreamWriter xmlStreamWriter) throws XMLStreamException {
        xmlStreamWriter.writeStartElement(MAGAZINES_PREFIX, MAGAZINES_TAG_NAME, MAGAZINES_NAMESPACE);
        xmlStreamWriter.writeNamespace(MAGAZINES_PREFIX, MAGAZINES_NAMESPACE);
        xmlStreamWriter.writeNamespace(XSI_PREFIX, XSI_NAMESPACE);
        xmlStreamWriter.writeAttribute(XSI_PREFIX, XSI_NAMESPACE, SCHEMA_LOCATION_PARAMETER, MAGAZINES_SCHEMA_LOCATION);
        for (Magazine magazine : magazines.getMagazines()) {
            writeMagazine(magazine, xmlStreamWriter);
        }
        xmlStreamWriter.writeEndElement();
    }

    private void writeMagazine(Magazine magazine, XMLStreamWriter xmlStreamWriter) throws XMLStreamException {
        xmlStreamWriter.writeStartElement(MAGAZINES_PREFIX, MAGAZINE_TAG_NAME, MAGAZINES_NAMESPACE);
        xmlStreamWriter.writeAttribute(ID_ATTRIBUTE, Integer.toString(magazine.getId()));
        writeElement(TITLE_TAG_NAME, magazine.getTitle(), xmlStreamWriter);
        writeElement(DESCRIPTION_TAG_NAME, magazine.getDescription(), xmlStreamWriter);
        writeElement(PRICE_TAG_NAME, Double.toString(magazine.getPrice()), xmlStreamWriter);
        writeElement(PER_MONTH_PUBLISH_COUNT_TAG_NAME, Integer.toString(magazine.getPerMonthPublishCount()), xmlStreamWriter);
        writeElement(CATEGORY_TAG_NAME, magazine.getCategory().value(), xmlStreamWriter);
        xmlStreamWriter.writeEndElement();
    }
}