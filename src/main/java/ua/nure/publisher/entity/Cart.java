package ua.nure.publisher.entity;

public class Cart {


    private String firstMounth;
    private String secondMounth;
int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    private int firstYear;
    private int secondYear;

    public String getFirstMounth() {
        return firstMounth;
    }

    public void setFirstMounth(String firstMounth) {
        this.firstMounth = firstMounth;
    }
}
