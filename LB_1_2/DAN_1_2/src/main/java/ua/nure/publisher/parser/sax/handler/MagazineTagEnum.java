package ua.nure.publisher.parser.sax.handler;

import static ua.nure.publisher.constants.ValueConstants.CATEGORY_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.DESCRIPTION_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_NAMESPACE;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.MAGAZINE_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.PER_MONTH_PUBLISH_COUNT_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.PRICE_TAG_NAME;
import static ua.nure.publisher.constants.ValueConstants.TITLE_TAG_NAME;

public enum MagazineTagEnum {

    MAGAZINES(MAGAZINES_TAG_NAME),
    MAGAZINE(MAGAZINE_TAG_NAME),
    TITLE(TITLE_TAG_NAME),
    DESCRIPTION(DESCRIPTION_TAG_NAME),
    PRICE(PRICE_TAG_NAME),
    CATEGORY(CATEGORY_TAG_NAME),
    PER_MONTH_PUBLISH_COUNT(PER_MONTH_PUBLISH_COUNT_TAG_NAME),
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
        this.nameSpace = MAGAZINES_NAMESPACE;
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
