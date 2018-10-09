package ua.nure.publisher.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ua.nure.publisher.constants.PathConstants.MAGAZINES_XSD_PATH;

import org.junit.Before;
import org.junit.Test;
import ua.nure.publisher.entity.Category;
import ua.nure.publisher.entity.Magazine;
import ua.nure.publisher.entity.Magazines;
import ua.nure.publisher.parser.dom.DomMagazineUnmarshallerImpl;
import ua.nure.publisher.parser.dom.DomMagazinesMarshallerImpl;
import ua.nure.publisher.parser.jaxb.JaxbMagazineMarshallerImpl;
import ua.nure.publisher.parser.jaxb.JaxbMagazineUnmarshallerImpl;
import ua.nure.publisher.parser.jdom.JdomMagazinesMarshallerImpl;
import ua.nure.publisher.parser.jdom.JdomMagazinesUnmarshallerImpl;
import ua.nure.publisher.parser.sax.SaxMagazinesUnmarshallerImpl;
import ua.nure.publisher.parser.stax.StaxMagazinesMarshallerImpl;
import ua.nure.publisher.parser.stax.StaxMagazinesUnmarshallerImpl;
import ua.nure.publisher.parser.util.XmlValidator;

public class ParsersTest {

    private static final String TEST_RESOURCES_FOLDER_PATH = "src/test/resources";
    private static final String MAGAZINES_XML_TEST_PATH = TEST_RESOURCES_FOLDER_PATH + "/xml/magazines.xml";

    private final Magazines magazines = new Magazines();
    private final Magazine magazine1 = new Magazine();
    private final Magazine magazine2 = new Magazine();

    @Before
    public void init(){
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
    }

    @Test
    public void testDomMarshalling() {
        MagazinesMarshaller marshaller = new DomMagazinesMarshallerImpl();
        MagazinesUnmarshaller unmarshaller = new DomMagazineUnmarshallerImpl();

        marshaller.marshal(magazines, MAGAZINES_XML_TEST_PATH);
        assertTrue(XmlValidator.validateAgainstXSD(MAGAZINES_XML_TEST_PATH, MAGAZINES_XSD_PATH));
        assertEquals(magazines, unmarshaller.unmarshal(MAGAZINES_XML_TEST_PATH));
    }

    @Test
    public void testJaxbMarshalling() {
        MagazinesMarshaller marshaller = new JaxbMagazineMarshallerImpl();
        MagazinesUnmarshaller unmarshaller = new JaxbMagazineUnmarshallerImpl();

        marshaller.marshal(magazines, MAGAZINES_XML_TEST_PATH);
        assertTrue(XmlValidator.validateAgainstXSD(MAGAZINES_XML_TEST_PATH, MAGAZINES_XSD_PATH));
        assertEquals(magazines, unmarshaller.unmarshal(MAGAZINES_XML_TEST_PATH));
    }

    @Test
    public void testJdomMarshalling() {
        MagazinesMarshaller marshaller = new JdomMagazinesMarshallerImpl();
        MagazinesUnmarshaller unmarshaller = new JdomMagazinesUnmarshallerImpl();

        marshaller.marshal(magazines, MAGAZINES_XML_TEST_PATH);
        assertTrue(XmlValidator.validateAgainstXSD(MAGAZINES_XML_TEST_PATH, MAGAZINES_XSD_PATH));
        assertEquals(magazines, unmarshaller.unmarshal(MAGAZINES_XML_TEST_PATH));
    }

    @Test
    public void testSaxMarshalling() {
        MagazinesUnmarshaller unmarshaller = new SaxMagazinesUnmarshallerImpl();

        assertEquals(magazines, unmarshaller.unmarshal(MAGAZINES_XML_TEST_PATH));
    }

    @Test
    public void testStaxMarshalling() {
        MagazinesMarshaller marshaller = new StaxMagazinesMarshallerImpl();
        MagazinesUnmarshaller unmarshaller = new StaxMagazinesUnmarshallerImpl();

        marshaller.marshal(magazines, MAGAZINES_XML_TEST_PATH);
        assertTrue(XmlValidator.validateAgainstXSD(MAGAZINES_XML_TEST_PATH, MAGAZINES_XSD_PATH));
        assertEquals(magazines, unmarshaller.unmarshal(MAGAZINES_XML_TEST_PATH));
    }

}