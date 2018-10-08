package ua.nure.publisher.parser.sax;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import ua.nure.publisher.entity.Magazines;
import ua.nure.publisher.parser.MagazinesUnmarshaller;
import ua.nure.publisher.parser.sax.handler.StaxMagazinesHandler;

import java.io.IOException;

public class SaxMagazinesUnmarshallerImpl implements MagazinesUnmarshaller {

    private static final Logger LOG = Logger.getLogger(SaxMagazinesUnmarshallerImpl.class);

    @Override
    public Magazines unmarshal(String filePath) {
        try {
            return unmarshalMagazines(filePath);
        } catch (SAXException | IOException e) {
            LOG.error("Failed to unmarshal magazines", e);
            return new Magazines();
        }
    }

    private Magazines unmarshalMagazines(String filePath) throws SAXException, IOException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        StaxMagazinesHandler handler = new StaxMagazinesHandler();
        reader.setContentHandler(handler);
        reader.parse(filePath);
        return handler.getMagazines();
    }

}
