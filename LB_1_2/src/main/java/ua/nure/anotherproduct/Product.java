package ua.nure.anotherproduct;

public class Product {

    protected int id;              // non required
    protected String name;         // required, non empty
    protected double price;        // >= 0
    protected String descr;        // non required
    protected String[] categories; // non required, non empty
    protected Char[] chars;        //required, can be different

}
