package ua.nure.publisher.parser.dom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import ua.nure.publisher.dto.Magazine;
import ua.nure.publisher.dto.Magazines;
import ua.nure.publisher.parser.MagazinesMarshaller;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

import static ua.nure.publisher.constants.PathConstants.MAGAZINES_XSD_PATH;
import static ua.nure.publisher.constants.ValueConstants.CATEGORY_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.DESCRIPTION_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.ID_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_NAMESPACE_URI;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINE_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.PRICE_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.TITLE_TAG_NAME;
import static ua.nure.publisher.parser.dom.util.DomMarshallingUtils.getSimpleElement;

public class DomMagazinesMarshallerImpl implements MagazinesMarshaller {

    private static final Logger LOG = LoggerFactory.getLogger(DomMagazinesMarshallerImpl.class);

    public void marshal(Magazines magazines, String filePath) {
        try {
            marshalMagazines(magazines, filePath);
        } catch (ParserConfigurationException | TransformerException | SAXException e) {
            LOG.error("Failed to marshal magazines", e);
        }
    }

    private void marshalMagazines(Magazines magazines, String filePath) throws ParserConfigurationException, SAXException, TransformerException {
        Document document = createDocument(magazines);
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(filePath);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(domSource, streamResult);
    }

    private Document createDocument(Magazines magazines) throws ParserConfigurationException, SAXException {
        DocumentBuilder documentBuilder = createDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element magazineElement = document.createElementNS(MAGAZINES_NAMESPACE_URI, MAGAZINES_TAG_NAME);
        document.appendChild(magazineElement);
        magazines.getMagazines().forEach(magazine -> magazineElement.appendChild(getMagazineElement(magazine, document)));
        return document;
    }

    private DocumentBuilder createDocumentBuilder() throws ParserConfigurationException, SAXException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File(MAGAZINES_XSD_PATH));
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setValidating(true);
        documentBuilderFactory.setSchema(schema);
        documentBuilderFactory.setNamespaceAware(true);
        return documentBuilderFactory.newDocumentBuilder();
    }

    private Element getMagazineElement(Magazine magazine, Document document) {
        Element magazineElement = document.createElementNS(MAGAZINES_NAMESPACE_URI, MAGAZINE_TAG_NAME);
        magazineElement.setAttribute(ID_ATTRIBUTE, String.valueOf(magazine.getId()));
        magazineElement.appendChild(getSimpleElement(document, MAGAZINES_NAMESPACE_URI, TITLE_TAG_NAME, magazine.getTitle()));
        magazineElement.appendChild(getSimpleElement(document, MAGAZINES_NAMESPACE_URI, DESCRIPTION_TAG_NAME, magazine.getDescription()));
        magazineElement.appendChild(getSimpleElement(document, MAGAZINES_NAMESPACE_URI, PRICE_TAG_NAME, magazine.getPrice()));
        magazineElement.appendChild(getSimpleElement(document, MAGAZINES_NAMESPACE_URI, CATEGORY_TAG_NAME, magazine.getCategory().value()));
        return magazineElement;
    }
}
