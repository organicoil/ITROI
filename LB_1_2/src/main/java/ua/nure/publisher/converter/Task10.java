package ua.nure.publisher.converter;

import static ua.nure.publisher.constants.PathConstants.*;

public class Task10 {

    public static void main(String[] args) {
        XmlToHtmlConverter converter = new XmlToHtmlConverter();
        converter.convertXMLToHTML(MODULE_PATH + TASK_10_BOOKS_XML_PATH, MODULE_PATH + TASK_10_BOOKS_XSL_PATH,
                MODULE_PATH + TASK_10_BOOKS_HTML_PATH);
    }

}
