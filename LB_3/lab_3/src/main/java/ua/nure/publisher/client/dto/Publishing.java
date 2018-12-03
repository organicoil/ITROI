package ua.nure.publisher.client.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *       &lt;sequence>
 *         &lt;element name="perMonthPublishCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0" form="qualified"/>
 *         &lt;element name="publishType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "publishing", propOrder = {
        "perMonthPublishCount",
        "publishType"
})
public class Publishing {

    protected Integer perMonthPublishCount;
    protected String publishType;

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
     * {@link String }
     */
    public String getPublishType() {
        return publishType;
    }

    /**
     * Sets the value of the publishType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPublishType(String value) {
        this.publishType = value;
    }

}
