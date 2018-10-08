package ua.nure.publisher.constants;

import org.jdom2.Namespace;

public class ValueConstants {

    public static final String ID_ATTRIBUTE = "id";
    public static final String TITLE_ATTRIBUTE = "title";
    public static final String DESCRIPTION_ATTRIBUTE = "description";
    public static final String PRICE_ATTRIBUTE = "price";
    public static final String PER_MONTH_PUBLISH_COUNT = "perMonthPublishCount";
    public static final String CATEGORY_ATTRIBUTE = "category";

    public static final String MAGAZINE_QUALIFIED_NAME = "magazine";
    public static final String MAGAZINES_QUALIFIED_NAME = "magazines";
    public static final String MAGAZINES_NAMESPACE_URI = "http://ua.nure/magazines/";
    public static final String MAGAZINES_SCHEMA_LOCATION = "http://ua.nure/magazines/ ../xsd/magazines.xsd";

    public static final String SCHEMA_LOCATION_PARAMETER = "schemaLocation";
    public static final Namespace XSI_NAMESPACE = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");

}
