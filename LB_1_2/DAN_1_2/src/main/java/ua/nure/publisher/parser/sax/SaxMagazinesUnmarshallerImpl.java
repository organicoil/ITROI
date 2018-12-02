package ua.nure.publisher.parser.sax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import ua.nure.publisher.entity.Magazines;
import ua.nure.publisher.parser.MagazinesUnmarshaller;
import ua.nure.publisher.parser.sax.handler.SaxMagazinesHandler;

import java.io.IOException;

public class SaxMagazinesUnmarshallerImpl implements MagazinesUnmarshaller {

    private static final Logger LOG = LoggerFactory.getLogger(SaxMagazinesUnmarshallerImpl.class);

    @Override
    public Magazines unmarshal(String filePath) {
        try {
            Magazines magazines = unmarshalMagazines(filePath);
            LOG.debug("Parsed magazines: {}", magazines);
            return magazines;
        } catch (SAXException | IOException e) {
            LOG.error("Failed to unmarshal magazines", e);
            return new Magazines();
        }
    }

    private Magazines unmarshalMagazines(String filePath) throws SAXException, IOException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        SaxMagazinesHandler handler = new SaxMagazinesHandler();
        reader.setContentHandler(handler);
        reader.parse(filePath);
        return handler.getMagazines();
    }
}
