
package ua.nure.publisher.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for publishing complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="publishing">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="perMonthPublishCount" type="{http://ua.nure/magazines/}perMonthPublishCount"/>
 *         &lt;element name="publishType" type="{http://ua.nure/magazines/}publishType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "publishing", namespace = "http://ua.nure/magazines/", propOrder = {
        "perMonthPublishCount",
        "publishType"
})
public class Publishing {

    @XmlElement(namespace = "http://ua.nure/magazines/")
    protected Integer perMonthPublishCount;
    @XmlElement(namespace = "http://ua.nure/magazines/")
    @XmlSchemaType(name = "string")
    protected PublishType publishType;

    /**
     * Gets the value of the perMonthPublishCount property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getPerMonthPublishCount() {
        return perMonthPublishCount;
    }

    /**
     * Sets the value of the perMonthPublishCount property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setPerMonthPublishCount(Integer value) {
        this.perMonthPublishCount = value;
    }

    /**
     * Gets the value of the publishType property.
     *
     * @return possible object is
     * {@link PublishType }
     */
    public PublishType getPublishType() {
        return publishType;
    }

    /**
     * Sets the value of the publishType property.
     *
     * @param value allowed object is
     *              {@link PublishType }
     */
    public void setPublishType(PublishType value) {
        this.publishType = value;
    }

}
