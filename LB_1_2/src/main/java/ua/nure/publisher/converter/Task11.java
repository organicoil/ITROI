package ua.nure.publisher.converter;

import static ua.nure.publisher.constants.PathConstants.*;

public class Task11 {

    public static void main(String[] args) {
        XmlToHtmlConverter converter = new XmlToHtmlConverter();
        converter.convertXMLToHTML(MODULE_PATH + TASK_11_BOOKS_XML_PATH, MODULE_PATH + TASK_11_BOOKS_XSL_PATH,
                MODULE_PATH + TASK_11_BOOKS_HTML_PATH);
    }

}
