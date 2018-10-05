package ua.nure.bookshop.parser.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import ua.nure.bookshop.entity.Book;
import ua.nure.bookshop.entity.BookShop;
import ua.nure.bookshop.entity.Category;
import ua.nure.bookshop.parser.BookUnmarshaller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxUnmarshaller implements ContentHandler, BookUnmarshaller {

    private BookShop shop = new BookShop();
    private Book currentBook;
    private BookTagEnum currentTag;
    private List<BookTagEnum> tags;

    public static void main(String[] arg) {
        SaxUnmarshaller saxUnmarshaller = new SaxUnmarshaller();
        BookShop bookShop = saxUnmarshaller.unmarshal("src/main/resources/xml/bookShop.xml");
        System.out.println(bookShop);
    }

    @Override
    public BookShop unmarshal(String filePath) {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(this);
            reader.parse(filePath);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return shop;
    }

    @Override
    public void setDocumentLocator(Locator locator) {
    }

    @Override
    public void startDocument() throws SAXException {
        tags = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        tags.add(BookTagEnum.getInstance(localName, uri));
        currentTag = tags.get(tags.size() - 1);
        if (currentTag.isBook()) {
            this.currentBook = new Book();
            currentBook.setId(Integer.parseInt(atts.getValue("id")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (currentTag.isBook()) {
            this.shop.getBook().add(currentBook);
            this.currentBook = null;
        }

        tags.remove(tags.size() - 1);
        currentTag = tags.size() > 0 ? tags.get(tags.size() - 1) : null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length);
        if (currentTag.isBookTitle()) {
            currentBook.setTitle(str);
        } else if (currentTag.isBookCategory()) {
            currentBook.setCategory(Category.fromValue(str));
        } else if (currentTag.isBookPrice()) {
            currentBook.setPrice(Double.parseDouble(str));
        } else if (currentTag.isAuthor()) {
            currentBook.getAuthor().add(str);
        } else if (currentTag.isISBN()) {
            currentBook.setISBN(str);
        }
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {

    }
}
