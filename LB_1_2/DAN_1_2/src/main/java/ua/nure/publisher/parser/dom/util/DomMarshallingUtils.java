package ua.nure.publisher.parser.dom.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomMarshallingUtils {

    public static Element getSimpleElement(Document document, String nameSpace, String tagName, Object value) {
        Element element = document.createElementNS(nameSpace, tagName);
        element.setTextContent(String.valueOf(value));
        return element;
    }
}
