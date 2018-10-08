package ua.nure.bookshop.parser.jaxb;

import static ua.nure.bookshop.constants.PathConstants.JAXB_XML_PATH;

import ua.nure.bookshop.entity.BookShop;
import ua.nure.bookshop.parser.BookMarshaller;
import ua.nure.bookshop.parser.Util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class JAXBMarshaller implements BookMarshaller {

    public void marshal(BookShop bookShop, String filePath) {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(BookShop.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://nure.ua/bookShop ../xsd/bookShop.xsd");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(bookShop, outputStream);
            marshaller.marshal(bookShop, System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) throws Exception {
        BookMarshaller jaxbMarshaller = new JAXBMarshaller();
        jaxbMarshaller.marshal(Util.createBookShop(), JAXB_XML_PATH);
    }
}
