package com.example.demoquery.projection;

import java.math.BigDecimal;


public class Transfer {
    private String id;

    public Transfer(String id, User userFrom, User userTo, BigDecimal amount) {
        this.id = id;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Transfer() {
    }

    private User userFrom;

    private User userTo;

    private BigDecimal amount;

    @Override
    public String toString() {
        return "Transfer{" +
                "id='" + id + '\'' +
                ", userFrom=" + userFrom +
                ", userTo=" + userTo +
                ", amount=" + amount +
                '}';
    }
}
