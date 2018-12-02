package ua.nure.publisher.converter;

import static ua.nure.publisher.constants.PathConstants.MAGAZINES_HTML_PATH;
import static ua.nure.publisher.constants.PathConstants.MAGAZINES_XML_PATH;
import static ua.nure.publisher.constants.PathConstants.MAGAZINES_XSL_PATH;
import static ua.nure.publisher.constants.PathConstants.MODULE_PATH;

public class XmlToHtmlConverterDemo {

    public static void main(String[] args) {
        XmlToHtmlConverter converter = new XmlToHtmlConverter();
        converter.convertXMLToHTML(MODULE_PATH + MAGAZINES_XML_PATH, MODULE_PATH + MAGAZINES_XSL_PATH,
                MODULE_PATH + MAGAZINES_HTML_PATH);
    }
}
