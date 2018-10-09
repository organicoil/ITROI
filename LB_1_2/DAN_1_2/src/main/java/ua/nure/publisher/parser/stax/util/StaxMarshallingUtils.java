package ua.nure.publisher.parser.stax.util;

import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_NAMESPACE;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class StaxMarshallingUtils {

    public static void writeElement(String name, String value, XMLStreamWriter xMLStreamWriter) throws XMLStreamException {
        xMLStreamWriter.writeStartElement("mag", name, MAGAZINES_NAMESPACE);
        xMLStreamWriter.writeCharacters(value);
        xMLStreamWriter.writeEndElement();
    }

}
