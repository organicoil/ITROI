package ua.nure.publisher.parser.dom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ua.nure.publisher.constants.PathConstants.MAGAZINES_XML_TEST_PATH;
import static ua.nure.publisher.constants.PathConstants.MAGAZINES_XSD_PATH;

import org.junit.Test;
import ua.nure.publisher.constants.PathConstants;
import ua.nure.publisher.entity.Category;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.entity.Magazines;
import ua.nure.publisher.parser.MagazinesMarshaller;
import ua.nure.publisher.parser.MagazinesUnmarshaller;
import ua.nure.publisher.parser.util.XmlValidator;

public class DomImplTest {

    @Test
    public void testMarshalling() {
        MagazinesMarshaller marshaller = new DomMagazinesMarshallerImpl();
        MagazinesUnmarshaller unmarshaller = new DomMagazineUnmarshallerImpl();

        Magazines magazines = new Magazines();
        Magazine magazine1 = new Magazine();
        Magazine magazine2 = new Magazine();

        magazine1.setId(1);
        magazine1.setTitle("Title1");
        magazine1.setDescription("Desc1");
        magazine1.setPrice(99);
        magazine1.setPerMonthPublishCount(4);
        magazine1.setCategory(Category.IT);

        magazine2.setId(2);
        magazine2.setTitle("Title2");
        magazine2.setDescription("Desc2");
        magazine2.setPrice(999);
        magazine2.setPerMonthPublishCount(3);
        magazine2.setCategory(Category.SPORT);

        magazines.add(magazine1);
        magazines.add(magazine2);

        marshaller.marshal(magazines, MAGAZINES_XML_TEST_PATH);
        assertTrue(XmlValidator.validateAgainstXSD(MAGAZINES_XML_TEST_PATH, MAGAZINES_XSD_PATH));
        assertEquals(magazines, unmarshaller.unmarshal(MAGAZINES_XML_TEST_PATH));
    }

}