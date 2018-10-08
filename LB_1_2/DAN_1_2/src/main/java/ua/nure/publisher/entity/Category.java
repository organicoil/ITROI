package ua.nure.publisher.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "category")
@XmlEnum
public enum Category {

    @XmlEnumValue("politics")
    POLITICS("politics"),
    @XmlEnumValue("it")
    IT("it"),
    @XmlEnumValue("sport")
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
