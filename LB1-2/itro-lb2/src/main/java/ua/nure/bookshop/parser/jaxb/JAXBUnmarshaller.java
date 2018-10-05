package ua.nure.bookshop.parser.jaxb;

import ua.nure.bookshop.entity.BookShop;
import ua.nure.bookshop.parser.BookUnmarshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBUnmarshaller implements BookUnmarshaller {

    public BookShop unmarshal(String filePath) {
        try {
            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(BookShop.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (BookShop) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] arg) {
        JAXBUnmarshaller jaxbUnmarshaller = new JAXBUnmarshaller();
        BookShop bookShop = jaxbUnmarshaller.unmarshal("src/main/resources/xml/bookShop.xml");
        System.out.println(bookShop.toString());
    }
}
