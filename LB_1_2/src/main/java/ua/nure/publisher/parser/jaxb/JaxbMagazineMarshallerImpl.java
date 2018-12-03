package ua.nure.publisher.parser.jaxb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.publisher.dto.Magazines;
import ua.nure.publisher.parser.MagazinesMarshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_SCHEMA_LOCATION;

public class JaxbMagazineMarshallerImpl implements MagazinesMarshaller {

    private static final Logger LOG = LoggerFactory.getLogger(JaxbMagazineMarshallerImpl.class);

    public void marshal(Magazines magazines, String filePath) {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            marshalMagazines(magazines, outputStream);
        } catch (JAXBException | IOException e) {
            LOG.error("Failed to marshal magazines", e);
        }
    }

    private void marshalMagazines(Magazines magazines, OutputStream outputStream) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Magazines.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, MAGAZINES_SCHEMA_LOCATION);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(magazines, outputStream);
    }
}
