package ua.nure.publisher.parser.jdom;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.publisher.dto.Magazine;
import ua.nure.publisher.dto.Magazines;
import ua.nure.publisher.parser.MagazinesMarshaller;

import java.io.FileOutputStream;
import java.io.IOException;

import static ua.nure.publisher.constants.ValueConstants.CATEGORY_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.DESCRIPTION_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.ID_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_NAMESPACE_URI;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_SCHEMA_LOCATION;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINE_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.PRICE_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.SCHEMA_LOCATION_PARAMETER;
import static ua.nure.publisher.constants.ValueConstants.TITLE_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.XSI_NAMESPACE;
import static ua.nure.publisher.parser.jdom.util.JdomMarshallingUtils.getSimpleElement;

public class JdomMagazinesMarshallerImpl implements MagazinesMarshaller {

    private static final Logger LOG = LoggerFactory.getLogger(JdomMagazinesMarshallerImpl.class);

    @Override
    public void marshal(Magazines magazines, String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            marshallMagazines(magazines, fileOut);
        } catch (IOException e) {
            LOG.error("Failed to marshal magazines", e);
        }
    }

    private void marshallMagazines(Magazines magazines, FileOutputStream fileOut) throws IOException {
        XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
        Document document = new Document(new Element(MAGAZINES_TAG_NAME, MAGAZINES_NAMESPACE_URI));
        document.getRootElement().addNamespaceDeclaration(XSI_NAMESPACE);
        document.getRootElement().setAttribute(SCHEMA_LOCATION_PARAMETER, MAGAZINES_SCHEMA_LOCATION, XSI_NAMESPACE);
        magazines.getMagazines().forEach((magazine) -> document.getRootElement().addContent(getBookElement(magazine)));
        xmlOut.output(document, fileOut);
    }

    private Element getBookElement(Magazine magazine) {
        Element elem = new Element(MAGAZINE_TAG_NAME, MAGAZINES_NAMESPACE_URI);
        elem.setAttribute(ID_ATTRIBUTE, String.valueOf(magazine.getId()));
        elem.addContent(getSimpleElement(TITLE_TAG_NAME, MAGAZINES_NAMESPACE_URI, magazine.getTitle()));
        elem.addContent(getSimpleElement(DESCRIPTION_TAG_NAME, MAGAZINES_NAMESPACE_URI, magazine.getDescription()));
        elem.addContent(getSimpleElement(PRICE_TAG_NAME, MAGAZINES_NAMESPACE_URI, magazine.getPrice()));
        elem.addContent(getSimpleElement(CATEGORY_TAG_NAME, MAGAZINES_NAMESPACE_URI, magazine.getCategory().value()));
        return elem;
    }
}
