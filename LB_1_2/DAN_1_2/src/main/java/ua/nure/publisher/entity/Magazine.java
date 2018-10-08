package ua.nure.publisher.entity;

import java.util.Objects;

public class Magazine extends Entity {

    private String title;
    private String description;
    private double price;
    private int perMonthPublishCount;
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
