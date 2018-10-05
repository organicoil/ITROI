package ua.nure.bookshop.parser.jdom;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import ua.nure.bookshop.entity.Book;
import ua.nure.bookshop.entity.BookShop;
import ua.nure.bookshop.parser.BookMarshaller;
import ua.nure.bookshop.parser.Util;

import java.io.FileOutputStream;
import java.io.IOException;

public class JDomMarshaller implements BookMarshaller {

    public static final Namespace BS_NS = Namespace.getNamespace("book", "http://nure.ua/bookShop");
    public static final Namespace XSI_NS = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");

    public static void main(String[] arg) throws IOException {
        BookMarshaller jDomMarshaller = new JDomMarshaller();
        jDomMarshaller.marshal(Util.createBookShop(), "src/main/resources/xml/jdom.xml");
    }

    @Override
    public void marshal(BookShop bookShop, String filePath) {
        XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());

        Document doc = new Document(new Element("bookShop", BS_NS));
        doc.getRootElement().addNamespaceDeclaration(XSI_NS);
        doc.getRootElement().setAttribute("schemaLocation", "http://nure.ua/bookShop ../xsd/bookShop.xsd", XSI_NS);
        for (Book book : bookShop.getBook()) {
            doc.getRootElement().addContent(getBookElement(book));
        }
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            xmlOut.output(doc, fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Element getBookElement(Book book) {
        Element elem = new Element("book", BS_NS);
        elem.setAttribute("id", String.valueOf(book.getId()));
        elem.addContent(getSimpleElement("title", BS_NS, book.getTitle()));
        book.getAuthor().forEach(author -> elem.addContent(getSimpleElement("author", BS_NS, author)));
        elem.addContent(getSimpleElement("ISBN", BS_NS, book.getISBN()));
        elem.addContent(getSimpleElement("price", BS_NS, book.getPrice()));
        elem.addContent(getSimpleElement("category", BS_NS, book.getCategory().value()));
        return elem;
    }

    private Element getSimpleElement(String name, Namespace ns, Object value) {
        Element el = new Element(name, ns);
        el.setText(String.valueOf(value));
        return el;
    }
}
