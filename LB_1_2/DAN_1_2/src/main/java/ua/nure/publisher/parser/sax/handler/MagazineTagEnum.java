package ua.nure.publisher.parser.sax.handler;

import static ua.nure.publisher.constants.ValueConstants.CATEGORY_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.DESCRIPTION_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_NAMESPACE_URI;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_QUALIFIED_NAME;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINE_QUALIFIED_NAME;
import static ua.nure.publisher.constants.ValueConstants.PER_MONTH_PUBLISH_COUNT_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.PRICE_ATTRIBUTE;
import static ua.nure.publisher.constants.ValueConstants.TITLE_ATTRIBUTE;

public enum MagazineTagEnum {

    MAGAZINES(MAGAZINES_QUALIFIED_NAME),
    MAGAZINE(MAGAZINE_QUALIFIED_NAME),
    TITLE(TITLE_ATTRIBUTE),
    DESCRIPTION(DESCRIPTION_ATTRIBUTE),
    PRICE(PRICE_ATTRIBUTE),
    CATEGORY(CATEGORY_ATTRIBUTE),
    PER_MONTH_PUBLISH_COUNT(PER_MONTH_PUBLISH_COUNT_ATTRIBUTE),
    NULL("");

    private String tagName;
    private String nameSpace;

    public static MagazineTagEnum getInstance(String tag, String nameSpace) {
        for (MagazineTagEnum e : MagazineTagEnum.values()) {
            if (e.getNameSpace().equals(nameSpace) && e.getTagName().equals(tag)) {
                return e;
            }
        }
        return NULL;
    }

    MagazineTagEnum(String tagName) {
        this.tagName = tagName;
        this.nameSpace = MAGAZINES_NAMESPACE_URI;
    }

    public boolean isMagazines() {
        return this == MAGAZINES;
    }

    public boolean isMagazine() {
        return this == MAGAZINE;
    }

    public String getTagName() {
        return tagName;
    }

    public String getNameSpace() {
        return nameSpace;
    }

}
