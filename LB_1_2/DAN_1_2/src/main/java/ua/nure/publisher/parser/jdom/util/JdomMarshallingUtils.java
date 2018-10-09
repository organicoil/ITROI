package ua.nure.publisher.parser.jdom.util;

import org.jdom2.Element;

public class JdomMarshallingUtils {

    public static Element getSimpleElement(String tagName, String nameSpace, Object value) {
        Element element = new Element(tagName, nameSpace);
        element.setText(String.valueOf(value));
        return element;
    }
}
