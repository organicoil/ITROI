package ua.nure.bookshop.parser.dom;

import static ua.nure.bookshop.constants.PathConstants.BOOK_SHOP_XSD_PATH;
import static ua.nure.bookshop.constants.PathConstants.DOM_XML_PATH;
import static ua.nure.bookshop.constants.ValueConstants.BS_NS;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import ua.nure.bookshop.entity.Book;
import ua.nure.bookshop.entity.BookShop;
import ua.nure.bookshop.parser.BookMarshaller;
import ua.nure.bookshop.parser.Util;

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
import java.io.IOException;

public class DomMarshaller implements BookMarshaller {

    public static void main(String[] arg) throws IOException {
        BookMarshaller parser = new DomMarshaller();
        parser.marshal(Util.createBookShop(), DOM_XML_PATH);
    }

    @Override
    public void marshal(BookShop bookShop, String filePath) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setValidating(true);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new File(BOOK_SHOP_XSD_PATH));
            documentBuilderFactory.setSchema(schema);
            documentBuilderFactory.setNamespaceAware(true);
        } catch (SAXException e) {
            e.printStackTrace();
        }

        try {
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element bookShopElement = document.createElementNS(BS_NS, "bookShop");
            document.appendChild(bookShopElement);

            bookShop.getBook().forEach(book -> bookShopElement.appendChild(getBookElement(book, document)));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filePath));
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private Element getBookElement(Book book, Document doc) {
        Element bookShopElement = doc.createElementNS(BS_NS, "book");
        bookShopElement.setAttribute("id", String.valueOf(book.getId()));
        bookShopElement.appendChild(getSimpleElement(doc, BS_NS, "title", book.getTitle()));
        book.getAuthor().forEach(
                author -> bookShopElement.appendChild(getSimpleElement(doc, BS_NS, "author", book.getAuthor())));
        bookShopElement.appendChild(getSimpleElement(doc, BS_NS, "ISBN", book.getISBN()));
        bookShopElement.appendChild(getSimpleElement(doc, BS_NS, "price", book.getPrice()));
        bookShopElement.appendChild(getSimpleElement(doc, BS_NS, "category", book.getCategory().value()));
        return bookShopElement;
    }

    private Element getSimpleElement(Document doc, String ns, String tagName, Object value) {
        Element element = doc.createElementNS(ns, tagName);
        element.setTextContent(String.valueOf(value));
        return element;
    }
}
