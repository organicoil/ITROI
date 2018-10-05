package ua.nure.publisher.entity;

import javax.xml.bind.annotation.XmlEnumValue;

public enum Category {

    POLITICS("politics"),
    IT("it"),
    SPORT("sport");
    private final String value;

    Category(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Category fromValue(String v) {
        for (Category c : Category.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
