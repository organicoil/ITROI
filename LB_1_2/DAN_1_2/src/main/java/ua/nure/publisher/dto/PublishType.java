
package ua.nure.publisher.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for publishType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="publishType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="daily"/>
 *     &lt;enumeration value="monthly"/>
 *     &lt;enumeration value="twicePerMonth"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "publishType", namespace = "http://ua.nure/magazines/")
@XmlEnum
public enum PublishType {

    @XmlEnumValue("daily")
    DAILY("daily"),
    @XmlEnumValue("monthly")
    MONTHLY("monthly"),
    @XmlEnumValue("twicePerMonth")
    TWICE_PER_MONTH("twicePerMonth");
    private final String value;

    PublishType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PublishType fromValue(String v) {
        for (PublishType c : PublishType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
