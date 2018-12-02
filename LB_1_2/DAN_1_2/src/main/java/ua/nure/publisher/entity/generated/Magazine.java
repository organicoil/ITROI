
package ua.nure.publisher.entity.generated;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *       &lt;all>
 *         &lt;element name="title" type="{http://ua.nure/magazines/}title"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="price" type="{http://ua.nure/magazines/}price"/>
 *         &lt;element name="publishing" type="{http://ua.nure/magazines/}publishing"/>
 *         &lt;element name="category" type="{http://ua.nure/magazines/}category"/>
 *       &lt;/all>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "magazine", namespace = "http://ua.nure/magazines/", propOrder = {
    "title",
    "description",
    "price",
    "publishing",
    "category"
})
public class Magazine
    extends Entity
{

    @XmlElement(namespace = "http://ua.nure/magazines/", required = true)
    protected String title;
    @XmlElement(namespace = "http://ua.nure/magazines/", required = true)
    protected String description;
    @XmlElement(namespace = "http://ua.nure/magazines/", required = true)
    protected BigDecimal price;
    @XmlElement(namespace = "http://ua.nure/magazines/", required = true)
    protected Publishing publishing;
    @XmlElement(namespace = "http://ua.nure/magazines/", required = true, defaultValue = "none")
    @XmlSchemaType(name = "string")
    protected Category category;

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     * Gets the value of the publishing property.
     * 
     * @return
     *     possible object is
     *     {@link Publishing }
     *     
     */
    public Publishing getPublishing() {
        return publishing;
    }

    /**
     * Sets the value of the publishing property.
     * 
     * @param value
     *     allowed object is
     *     {@link Publishing }
     *     
     */
    public void setPublishing(Publishing value) {
        this.publishing = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link Category }
     *     
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link Category }
     *     
     */
    public void setCategory(Category value) {
        this.category = value;
    }

}
