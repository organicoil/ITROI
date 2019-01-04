package ua.nure.product;

public class Product {

    protected int id;              // attribute, non required
    protected String name;         // required, non empty
    protected double price;        // >= 0
    protected String descr;        // non required
    protected String[] categories; // non required, non empty

}
