package ua.nure.publisher.entity;

import java.io.Serializable;
import java.security.Timestamp;

public class Commentary implements Serializable {

    private int id;
    private int products_id;
    private String message;
    private Integer users_id;
    private Timestamp date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProducts_id() {
        return products_id;
    }

    public void setProducts_id(int products_id) {
        this.products_id = products_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUsers_id() {
        return users_id;
    }

    public void setUsers_id(Integer users_id) {
        this.users_id = users_id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commentary{" +
                "id=" + id +
                ", products_id=" + products_id +
                ", content='" + message + '\'' +
                ", users_id=" + users_id +
                ", date=" + date +
                '}';
    }
}


