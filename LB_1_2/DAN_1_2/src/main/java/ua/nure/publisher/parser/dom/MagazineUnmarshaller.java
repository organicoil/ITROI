package ua.nure.publisher.parser.dom;

import static ua.nure.publisher.constants.ValueConstants.CATEGORY_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.ID_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINE_NAMESPACE_URI;
import static ua.nure.publisher.constants.ValueConstants.PER_MONTH_PUBLISH_COUNT;
import static ua.nure.publisher.constants.ValueConstants.PRICE_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.TITLE_ATTRIBUTE;
import static ua.nure.publisher.util.UnmarshallingUtils.getValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ua.nure.publisher.entity.Category;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.entity.Magazines;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MagazineUnmarshaller {

    private static final Logger LOG = LoggerFactory.getLogger(MagazineUnmarshaller.class);

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
        return new Magazines();
    }

    private Magazines getMagazinesFromNodeList(NodeList magazineNodeList) {
        Magazines magazines = new Magazines();
        for (int index = 0; index < magazineNodeList.getLength(); index++) {
            magazines.getMagazines().add(getMagazineByIndex(magazineNodeList, index));
        }
        return magazines;
    }

    private Magazine getMagazineByIndex(NodeList magazineNodeList, int index) {
        Node magazineNode = magazineNodeList.item(index);
        if (magazineNode.getNodeType() == Node.ELEMENT_NODE) {
            return parseMagazine((Element) magazineNode);
        }
        return null;
    }

    private Magazine parseMagazine(Element magazineElement) {
        Magazine magazine = new Magazine();
        magazine.setId(Integer.parseInt(magazineElement.getAttribute(ID_ATTRIBUTE)));
        magazine.setTitle(getValue(magazineElement, MAGAZINE_NAMESPACE_URI, TITLE_ATTRIBUTE));
        magazine.setPrice(Double.parseDouble(getValue(magazineElement, MAGAZINE_NAMESPACE_URI, PRICE_ATTRIBUTE)));
        magazine.setPerMonthPublishCount(Integer.parseInt(getValue(magazineElement, MAGAZINE_NAMESPACE_URI, PER_MONTH_PUBLISH_COUNT)));
        magazine.setCategory(Category.fromValue(getValue(magazineElement, MAGAZINE_NAMESPACE_URI, CATEGORY_ATTRIBUTE)));
        return magazine;
    }

}

