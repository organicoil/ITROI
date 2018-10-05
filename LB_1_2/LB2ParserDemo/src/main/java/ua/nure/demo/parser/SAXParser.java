package ua.nure.demo.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.demo.constants.Constants;
import ua.nure.order.entity.book.Author;
import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.book.Category;
import ua.nure.order.entity.order.Order;
import ua.nure.order.entity.order.OrderItem;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SAXParser extends DefaultHandler {
    private static boolean logEnabled = true;

    private static void log(Object o) {
        if (logEnabled) {
            System.out.println(o);
        }
    }

    private String current;
    private List<Order> orders;
    private OrderItem item;
    private Book book;
    private String countParent;
    private Author author;
    private String titleParent;
    private Order order;

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public void error(org.xml.sax.SAXParseException e) throws SAXException {
        throw e; // throw exception if xml not valid
    }

    private List<Order> parse(InputStream in) throws ParserConfigurationException, SAXException, IOException {
        List<Order> orders = new ArrayList<>();

        SAXParserFactory factory = SAXParserFactory.newInstance();

        factory.setNamespaceAware(true);
        // make parser validating
        factory.setFeature(Constants.FEATURE__TURN_VALIDATION_ON, true);
        factory.setFeature(Constants.FEATURE__TURN_SCHEMA_VALIDATION_ON, true);

        javax.xml.parsers.SAXParser parser = factory.newSAXParser();
        parser.parse(in, this);

        return orders;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        current = localName;

        if (Constants.TAG_ORDERS.equals(current)) {
            orders = new ArrayList<>();
        } else if (Constants.TAG_ORDER.equals(current)) {
            order = new Order();
        } else if (Constants.TAG_ORDER_ITEM.equals(current)) {
            countParent = Constants.TAG_ORDER_ITEM;
            item = new OrderItem();
            if (attributes.getLength() > 0) {
                item.setId(Integer.parseInt(attributes.getValue("id")));
            }
        } else if (Constants.TAG_BOOK.equals(current)) {
            book = new Book();
            if (attributes.getLength() > 0) {
                book.setId(Integer.parseInt(attributes.getValue("id")));
            }
            countParent = Constants.TAG_BOOK;
            titleParent = Constants.TAG_BOOK;
        } else if (Constants.TAG_AUTHOR.equals(current)) {
            author = new Author();
            if (attributes.getLength() > 0) {
                author.setId(Integer.parseInt(attributes.getValue("id")));
            }
            titleParent = Constants.TAG_AUTHOR;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (Constants.TAG_COUNT.equals(current)) {
            if (Constants.TAG_ORDER_ITEM.equals(countParent)) {
                item.setCount(Integer.parseInt(new String(ch, start, length)));
            }
            if (Constants.TAG_BOOK.equals(countParent)) {
                book.setCount(Integer.parseInt(new String(ch, start, length)));
            }
        } else if (Constants.TAG_TITLE.equals(current)) {
            if (Constants.TAG_BOOK.equals(titleParent)) {
                book.setTitle(new String(ch, start, length));
            }
            if (Constants.TAG_AUTHOR.equals(titleParent)) {
                author.setTitle(new String(ch, start, length));
            }
        } else if (Constants.TAG_CATEGORY.equals(current)) {
            book.setCategory(Category.fromValue(new String(ch, start, length)));
        } else if (Constants.TAG_PRICE.equals(current)) {
            book.setPrice(Double.parseDouble(new String(ch, start, length)));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (Constants.TAG_ORDER.equals(localName)) {
            orders.add(order);
            log(current + " " + order);
        } else if (Constants.TAG_ORDER_ITEM.equals(localName)) {
            order.getOrderItem().add(item);
//			log(current + " " + item);
        } else if (Constants.TAG_BOOK.equals(localName)) {
            item.setBook(book);
//			log(current + " " + book);
        } else if (Constants.TAG_AUTHOR.equals(localName)) {
            book.getAuthor().add(author);
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParser parser = new SAXParser();
        parser.parse(new FileInputStream("xml/orders.xml"));
        System.out.println(parser.getOrders());
    }
}
