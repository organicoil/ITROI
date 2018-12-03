package ua.nure.publisher.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

    private int id;
    private double price;
    private String date;
    private int user_id;
    private int magazine_id;
    private String firstMounth;
    private String secondMounth;
    private int firstYear;
    private int secondYear;
    private  String status;

    public int getMagazine_id() {
        return magazine_id;
    }

    public void setMagazine_id(int magazine_id) {
        this.magazine_id = magazine_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSecondYear() {
        return secondYear;
    }

    public void setSecondYear(int secondYear) {
        this.secondYear = secondYear;
    }

    public String getSecondMounth() {
        return secondMounth;
    }

    public void setSecondMounth(String secondMounth) {
        this.secondMounth = secondMounth;
    }

    public int getFirstYear() {
        return firstYear;
    }

    public void setFirstYear(int firstYear) {
        this.firstYear = firstYear;
    }

    public String getFirstMounth() {

        return firstMounth;
    }

    public void setFirstMounth(String firstMounth) {
        this.firstMounth = firstMounth;
    }

    public List<Magazine> getMagazines() {
        return magazines;
    }

    public void setMagazines(List<Magazine> magazines) {
        this.magazines = magazines;
    }

    List<Magazine> magazines=new ArrayList<>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (Double.compare(order.price, price) != 0) return false;
        if (!date.equals(order.date)) return false;
        return status == order.status;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + date.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrderDAO{" +
                "id=" + id +
                ", price=" + price +
                ", date=" + date +
                ", status=" + status +
                ",user_id="+user_id+
                '}';
    }
}
