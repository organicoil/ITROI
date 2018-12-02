package com.company.entity;

import java.time.LocalDateTime;

public class Payment {
    private LocalDateTime date;
    private int id;

    private User payer;
    private User recipient;

    private double amount;

    @Override
    public String toString() {
        return "Payment{" +
                "date=" + date +
                ", id=" + id +
                ", payer=" + payer +
                ", recipient=" + recipient +
                ", amount=" + amount +
                ", currency=" + currency +
                ", info=" + info +
                '}';
    }

    private Currency currency;

    private Info info;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
