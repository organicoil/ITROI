package ua.nure.demo;

import ua.nure.demo.parser.DOMParser;
import ua.nure.demo.parser.JAXBParser;
import ua.nure.demo.parser.SAXParser;

public class Demo {

    public static void main(String[] args) throws Exception {
        DOMParser.main(args);
        SAXParser.main(args);
        JAXBParser.main(args);
    }
}
