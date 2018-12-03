package ua.nure.publisher.client.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for magazine complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="magazine">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ua.nure/magazines/}entity">
 *       &lt;sequence>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double" form="qualified"/>
 *         &lt;element name="publishing" type="{http://ua.nure/magazines/}publishing" form="qualified"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "magazine", propOrder = {
        "title",
        "description",
        "price",
        "publishing",
        "category"
})
public class Magazine extends Entity {

    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String description;
    protected double price;
    @XmlElement(required = true)
    protected Publishing publishing;
    @XmlElement(required = true, defaultValue = "none")
    protected String category;

    /**
     * Gets the value of the title property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the price property.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the publishing property.
     *
     * @return possible object is
     * {@link Publishing }
     */
    public Publishing getPublishing() {
        return publishing;
    }

    /**
     * Sets the value of the publishing property.
     *
     * @param value allowed object is
     *              {@link Publishing }
     */
    public void setPublishing(Publishing value) {
        this.publishing = value;
    }

    /**
     * Gets the value of the category property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCategory(String value) {
        this.category = value;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "id='" + getId() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", category=" + getCategory() +
                '}';
    }
}
