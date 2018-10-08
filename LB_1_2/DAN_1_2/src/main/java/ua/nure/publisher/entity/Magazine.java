package ua.nure.publisher.entity;

import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_NAMESPACE_URI;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "magazine", propOrder = {
        "title",
        "description",
        "price",
        "perMonthPublishCount",
        "category"})
public class Magazine extends Entity {

    @XmlElement(name = "title", namespace = MAGAZINES_NAMESPACE_URI)
    private String title;
    @XmlElement(name = "description", namespace = MAGAZINES_NAMESPACE_URI)
    private String description;
    @XmlElement(name = "price", namespace = MAGAZINES_NAMESPACE_URI)
    private double price;
    @XmlElement(name = "perMonthPublishCount", namespace = MAGAZINES_NAMESPACE_URI)
    private int perMonthPublishCount;
    @XmlElement(name = "category", namespace = MAGAZINES_NAMESPACE_URI)
    @XmlSchemaType(name = "string")
    private Category category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPerMonthPublishCount() {
        return perMonthPublishCount;
    }

    public void setPerMonthPublishCount(int perMonthPublishCount) {
        this.perMonthPublishCount = perMonthPublishCount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Magazine)) {
            return false;
        }
        Magazine magazine = (Magazine) o;
        return Double.compare(magazine.getPrice(), getPrice()) == 0 &&
                getId() == magazine.getId() &&
                getPerMonthPublishCount() == magazine.getPerMonthPublishCount() &&
                Objects.equals(getTitle(), magazine.getTitle()) &&
                Objects.equals(getDescription(), magazine.getDescription()) &&
                getCategory() == magazine.getCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getPrice(), getPerMonthPublishCount(), getCategory());
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "id='" + getId() + '\'' +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", perMonthPublishCount=" + getPerMonthPublishCount() +
                ", category=" + getCategory() +
                '}';
    }

}
