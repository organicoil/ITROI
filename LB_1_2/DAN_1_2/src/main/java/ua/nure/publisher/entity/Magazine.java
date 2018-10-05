package ua.nure.publisher.entity;

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
}
