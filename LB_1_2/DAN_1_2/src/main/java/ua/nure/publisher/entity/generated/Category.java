
package ua.nure.publisher.entity.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for category.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="category">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="politics"/>
 *     &lt;enumeration value="sport"/>
 *     &lt;enumeration value="it"/>
 *     &lt;enumeration value="none"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "category", namespace = "http://ua.nure/magazines/")
@XmlEnum
public enum Category {

    @XmlEnumValue("politics")
    POLITICS("politics"),
    @XmlEnumValue("sport")
    SPORT("sport"),
    @XmlEnumValue("it")
    IT("it"),
    @XmlEnumValue("none")
    NONE("none");
    private final String value;

    Category(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Category fromValue(String v) {
        for (Category c: Category.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
