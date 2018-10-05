package ua.nure.demo.parser;

import static ua.nure.demo.constants.Constants.ORDERS_XML_PATH;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.demo.constants.Constants;
import ua.nure.order.entity.book.Author;
import ua.nure.order.entity.book.Book;
import ua.nure.order.entity.book.Category;
import ua.nure.order.entity.order.Order;
import ua.nure.order.entity.order.OrderItem;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {

    private static void log(Object o) {
        System.out.println(o);
    }

    private Order parseOrder(Node node) {
        Order order = new Order();
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            log(item.getLocalName());
            if (Constants.TAG_ORDER_ITEM.equals(item.getLocalName())) {
                order.getOrderItem().add(parseOrderItem(item));
            }
        }
        return order;
    }

    private OrderItem parseOrderItem(Node node) {
        OrderItem oItem = new OrderItem();
        if (node.hasAttributes()) {
            NamedNodeMap attrs = node.getAttributes();
            Node item = attrs.getNamedItem(Constants.ATTR_ID);
            log(item.getLocalName() + " = " + item.getTextContent());
            oItem.setId(Integer.parseInt(item.getTextContent()));
        }
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            log(item.getLocalName());
            if (Constants.TAG_COUNT.equals(item.getLocalName())) {
                log(item.getLocalName() + " = " + item.getTextContent());
                oItem.setCount(Integer.parseInt(item.getTextContent()));
            } else if (Constants.TAG_BOOK.equals(item.getLocalName())) {
                log(item.getLocalName() + " = " + item.getTextContent());
                oItem.setBook(parseBook(item));
            }
        }
        return oItem;
    }

    private Book parseBook(Node node) {
        Book book = new Book();
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            log(item.getLocalName() + " NS = " + item.getPrefix() + " local = " + item.getLocalName());
            if (Constants.TAG_COUNT.equals(item.getLocalName())) {
                log(item.getLocalName() + " = " + item.getTextContent());
                book.setCount(Integer.parseInt(item.getTextContent()));
            } else if (Constants.TAG_TITLE.equals(item.getLocalName())) {
                log(item.getLocalName() + " = " + item.getTextContent());
                book.setTitle(item.getTextContent());
            } else if (Constants.TAG_AUTHOR.equals(item.getLocalName())) {
                Author author = new Author();
                if (node.hasAttributes()) {
                    NamedNodeMap attrs = node.getAttributes();
                    Node attr = attrs.getNamedItem(Constants.ATTR_ID);
                    log(attr.getNodeName() + " = " + attr.getTextContent());
                    author.setId(Integer.parseInt(attr.getTextContent()));
                }
                log(item.getLocalName() + " = " + item.getTextContent());
                author.setTitle(item.getTextContent());
                book.getAuthor().add(author);
            } else if (Constants.TAG_CATEGORY.equals(item.getLocalName())) {
                log(item.getLocalName() + " = " + item.getTextContent());
                book.setCategory(Category.fromValue(item.getTextContent()));
            } else if (Constants.TAG_ISBN.equals(item.getLocalName())) {
                log(item.getLocalName() + " = " + item.getTextContent());
                book.setTitle(item.getTextContent());
            } else if (Constants.TAG_PRICE.equals(item.getLocalName())) {
                log(item.getLocalName() + " = " + item.getTextContent());
                book.setPrice(Double.parseDouble(item.getTextContent()));
            }
        }
        return book;
    }

    private List<Order> parse(InputStream in) throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);

        // make parser validating
        dbf.setFeature(Constants.FEATURE__TURN_VALIDATION_ON, true);
        dbf.setFeature(Constants.FEATURE__TURN_SCHEMA_VALIDATION_ON, true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        db.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException e) throws SAXException {
                throw e; // <-- throw exception if XML document is NOT valid
            }
        });

        Document root = db.parse(in);

        List<Order> orders = new ArrayList<>();

        Element e = root.getDocumentElement();
        NodeList xmlOrders = e.getElementsByTagName(Constants.TAG_ORDER);
        for (int i = 0; i < xmlOrders.getLength(); i++) {
            orders.add(parseOrder(xmlOrders.item(i)));
        }
        // String id = e.getAttribute(Constants.ATTR_ID);
        return orders;
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DOMParser domParser = new DOMParser();
        InputStream in = new FileInputStream(ORDERS_XML_PATH);
        List<Order> orders = domParser.parse(in);
        System.out.println(orders);
    }
}
