package ua.nure.demo.constants;

import ua.nure.order.entity.order.ObjectFactory;

public class Constants {

    private static final String RESOURCES_FOLDER_PATH = "D:/Study/Andrusha/Publisher/LB_1_2/LB2ParserDemo/src/main/resources";

    public static final String ORDERS_XML_PATH = RESOURCES_FOLDER_PATH + "/xml/orders.xml";
    public static final String INVALID_ORDERS_XML_FILE = RESOURCES_FOLDER_PATH + "/xml/invalid_orders.xml";
    public static final String ORDERS_XSD_FILE = RESOURCES_FOLDER_PATH + "/xsd/orders.xsd";

    public static final String TAG_ORDERS = "orders";
    public static final String TAG_ORDER = "order";
    public static final String TAG_ORDER_ITEM = "orderItem";
    public static final String ATTR_ID = "id";
    public static final String TAG_COUNT = "count";
    public static final String TAG_BOOK = "book";
    public static final String TAG_TITLE = "title";
    public static final String TAG_AUTHOR = "author";
    public static final String TAG_PRICE = "price";
    public static final String TAG_CATEGORY = "category";
    public static final String TAG_ISBN = "isbn";

    public static final Class OBJECT_FACTORY = ObjectFactory.class;

    public static final String SCHEMA_LOCATION__ATTR_NAME = "schemaLocation";
    public static final String SCHEMA_LOCATION__ATTR_FQN = "xsi:schemaLocation";
    public static final String XSI_SPACE__PREFIX = "xsi";
    public static final String SCHEMA_LOCATION_URI = "http://order.nure.ua/entity/order/ orders.xsd";

    // validation features
    public static final String FEATURE__TURN_VALIDATION_ON = "http://xml.org/sax/features/validation";
    public static final String FEATURE__TURN_SCHEMA_VALIDATION_ON = "http://apache.org/xml/features/validation/schema";
}
