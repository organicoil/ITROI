package ua.nure.publisher.parser.jdom;

import static ua.nure.publisher.constants.ValueConstants.CATEGORY_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.DESCRIPTION_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.ID_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_NAMESPACE_URI;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINE_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.PER_MONTH_PUBLISH_COUNT_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.PRICE_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.TITLE_TAG_NAME;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import ua.nure.publisher.entity.Category;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.entity.Magazines;
import ua.nure.publisher.parser.MagazinesUnmarshaller;

import java.io.IOException;

public class JdomMagazinesUnmarshallerImpl implements MagazinesUnmarshaller {

    private static final Logger LOG = Logger.getLogger(JdomMagazinesUnmarshallerImpl.class);

    @Override
    public Magazines unmarshal(String filePath) {
        try {
            return unmarshalMagazines(filePath);
        } catch (JDOMException | IOException e) {
            LOG.error("Failed to unmarshal magazines");
            return new Magazines();
        }
    }

    private Magazines unmarshalMagazines(String filePath) throws JDOMException, IOException {
        Magazines magazines = new Magazines();
        SAXBuilder builder = new SAXBuilder(XMLReaders.NONVALIDATING);
        Document doc = builder.build(filePath);
        Element bookShopElement = doc.getRootElement();
        for (Element child : bookShopElement.getChildren()) {
            if (MAGAZINE_TAG_NAME.equals(child.getName())) {
                Magazine magazine = getMagazine(child);
                if (magazine != null) {
                    magazines.add(magazine);
                }
            }
        }
        return magazines;
    }

    private static Magazine getMagazine(Element elem) {
        try {
            Namespace namespace = Namespace.getNamespace(MAGAZINE_TAG_NAME, MAGAZINES_NAMESPACE_URI);
            Magazine magazine = new Magazine();
            magazine.setId(elem.getAttribute(ID_ATTRIBUTE).getIntValue());
            magazine.setTitle(elem.getChild(TITLE_TAG_NAME, namespace).getText());
            magazine.setDescription(elem.getChild(DESCRIPTION_TAG_NAME, namespace).getText());
            magazine.setPrice(Double.parseDouble(elem.getChild(PRICE_TAG_NAME, namespace).getText()));
            magazine.setPerMonthPublishCount(Integer.parseInt(elem.getChild(PER_MONTH_PUBLISH_COUNT_TAG_NAME, namespace).getText()));
            magazine.setCategory(Category.fromValue(elem.getChildText(CATEGORY_TAG_NAME, namespace)));
            return magazine;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
