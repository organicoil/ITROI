package ua.nure.publisher.parser.dom;

import static ua.nure.publisher.constants.ValueConstants.CATEGORY_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.DESCRIPTION_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.ID_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_NAMESPACE;
import static ua.nure.publisher.constants.ValueConstants.PER_MONTH_PUBLISH_COUNT_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.PRICE_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.TITLE_TAG_NAME;
import static ua.nure.publisher.parser.dom.util.DomUnmarshallingUtils.getValue;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ua.nure.publisher.entity.Category;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.entity.Magazines;
import ua.nure.publisher.parser.MagazinesUnmarshaller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DomMagazineUnmarshallerImpl implements MagazinesUnmarshaller {

    private static final Logger LOG = Logger.getLogger(DomMagazineUnmarshallerImpl.class);

    public Magazines unmarshal(String filePath) {
        try {
            return unmarshalMagazines(filePath);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOG.error("Failed to un-marshal magazines", e);
            return new Magazines();
        }
    }

    private Magazines unmarshalMagazines(String filePath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(filePath);
        return parseMagazines(document);
    }

    private Magazines parseMagazines(Document document) {
        Element magazineElement = document.getDocumentElement();
        if (magazineElement != null) {
            NodeList magazineNodeList = magazineElement.getChildNodes();
            return getMagazinesFromNodeList(magazineNodeList);
        }
        return null;
    }

    private Magazines getMagazinesFromNodeList(NodeList magazineNodeList) {
        Magazines magazines = new Magazines();
        for (int index = 0; index < magazineNodeList.getLength(); index++) {
            Node magazineNode = magazineNodeList.item(index);
            if (magazineNode.getNodeType() == Node.ELEMENT_NODE) {
                magazines.add(parseMagazine((Element) magazineNode));
            }
        }
        return magazines;
    }

    private Magazine parseMagazine(Element magazineElement) {
        Magazine magazine = new Magazine();
        magazine.setId(Integer.parseInt(magazineElement.getAttribute(ID_ATTRIBUTE)));
        magazine.setTitle(getValue(magazineElement, MAGAZINES_NAMESPACE, TITLE_TAG_NAME));
        magazine.setDescription(getValue(magazineElement, MAGAZINES_NAMESPACE, DESCRIPTION_TAG_NAME));
        magazine.setPrice(Double.parseDouble(getValue(magazineElement, MAGAZINES_NAMESPACE, PRICE_TAG_NAME)));
        magazine.setPerMonthPublishCount(Integer.parseInt(getValue(magazineElement, MAGAZINES_NAMESPACE,
                PER_MONTH_PUBLISH_COUNT_TAG_NAME)));
        magazine.setCategory(Category.fromValue(getValue(magazineElement, MAGAZINES_NAMESPACE, CATEGORY_TAG_NAME)));
        return magazine;
    }

}

