package ua.nure.publisher.parser.jaxb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.publisher.entity.Magazines;
import ua.nure.publisher.parser.MagazinesUnmarshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbMagazineUnmarshallerImpl implements MagazinesUnmarshaller {

    private static final Logger LOG = LoggerFactory.getLogger(JaxbMagazineMarshallerImpl.class);

    public Magazines unmarshal(String filePath) {
        try {
            Magazines magazines = unmarshalMagazines(filePath);
            LOG.debug("Parsed magazines: {}", magazines);
            return magazines;
        } catch (JAXBException e) {
            LOG.error("Failed to marshal magazines", e);
            return new Magazines();
        }
    }

    private Magazines unmarshalMagazines(String filePath) throws JAXBException {
        File file = new File(filePath);
        JAXBContext jaxbContext = JAXBContext.newInstance(Magazines.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Magazines) jaxbUnmarshaller.unmarshal(file);
    }
}
