package ua.nure.publisher.constants;

import org.jdom2.Namespace;

public class ValueConstants {

    public static final String ID_ATTRIBUTE = "id";
    public static final String TITLE_TAG_NAME = "title";
    public static final String DESCRIPTION_TAG_NAME = "description";
    public static final String PRICE_TAG_NAME = "price";
    public static final String PER_MONTH_PUBLISH_COUNT_TAG_NAME = "perMonthPublishCount";
    public static final String CATEGORY_TAG_NAME = "category";
    public static final String MAGAZINE_TAG_NAME = "magazine";
    public static final String MAGAZINES_TAG_NAME = "magazines";

    public static final String MAGAZINES_PREFIX = "mag";
    public static final String MAGAZINES_NAMESPACE_URI = "http://ua.nure/magazines/";
    public static final String MAGAZINES_SCHEMA_LOCATION = "http://ua.nure/magazines/ ../xsd/magazines.xsd";

    public static final String XSI_PREFIX = "xsi";
    public static final String XSI_NAMESPACE_URI = "http://www.w3.org/2001/XMLSchema-instance";

    public static final String SCHEMA_LOCATION_PARAMETER = "schemaLocation";
    public static final String UTF_ENCODING = "UTF-8";
    public static final String UTF_ENCODING_VERSION = "1.0";

    public static final Namespace XSI_NAMESPACE = Namespace.getNamespace(XSI_PREFIX, XSI_NAMESPACE_URI);

}
