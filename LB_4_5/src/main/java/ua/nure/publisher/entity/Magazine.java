package ua.nure.publisher.entity;

import java.io.Serializable;
import java.util.Arrays;

public class Magazine implements Serializable {

    /**
     * Magazine entity
     *
     *
     */

    private int id;
    private String name;
    private String description;
    private String theme;
    private int quantityInMounth;
    private double price;
    private byte[] byteImage;
    private String imgInBase64;
    private byte[] img;


    public void setImgInBase64(String imgInBase64) {
        this.imgInBase64 = imgInBase64;
    }
    public String getImgInBase64() {
        return imgInBase64;
    }
    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getQuantityInMounth() {
        return quantityInMounth;
    }

    public void setQuantityInMounth(int quantityInMounth) {
        this.quantityInMounth = quantityInMounth;
    }

    public byte[] getByteImage() {
        return byteImage;
    }

    public void setByteImage(byte[] byteImage) {
        this.byteImage = byteImage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Magazine magazine = (Magazine) o;

        if (id != magazine.id) return false;
        if (quantityInMounth != magazine.quantityInMounth) return false;
        if (Double.compare(magazine.price, price) != 0) return false;
        if (!name.equals(magazine.name)) return false;
        if (!description.equals(magazine.description)) return false;
        if (!theme.equals(magazine.theme)) return false;
        return Arrays.equals(byteImage, magazine.byteImage);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + theme.hashCode();
        result = 31 * result + quantityInMounth;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + Arrays.hashCode(byteImage);
        return result;
    }

    @Override
    public String toString() {
        return "MagazineServlet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", theme='" + theme + '\'' +
                ", quantityInMounth=" + quantityInMounth +
                ", price=" + price +
                '}';
    }
}
