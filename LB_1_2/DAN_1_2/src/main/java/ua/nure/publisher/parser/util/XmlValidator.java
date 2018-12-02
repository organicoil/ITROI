package ua.nure.publisher.parser.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

    private static final Logger LOG = LoggerFactory.getLogger(XmlValidator.class);

    public static boolean validateAgainstXSD(String xmlFilePath, String xsdFilePath) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(xsdFilePath));
            StreamSource xmlStreamSource = new StreamSource(new File(xmlFilePath));
            Validator validator = schema.newValidator();
            validator.validate(xmlStreamSource);
            return true;
        } catch (SAXException | IOException e) {
            LOG.warn("Error while validating xml file", e);
            return false;
        }
    }
}
