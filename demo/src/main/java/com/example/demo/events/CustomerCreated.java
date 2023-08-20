package com.example.demo.events;

import com.example.demo.entity.User;

import java.time.LocalDateTime;

public class CustomerCreated {
    private final User user;
    private final LocalDateTime zonedDateTime;

    public CustomerCreated(User user, LocalDateTime zonedDateTime) {
        this.user = user;
        this.zonedDateTime = zonedDateTime;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    @Override
    public String toString() {
        return "CustomerCreated{" +
                "user=" + user.toString() +
                ", zonedDateTime=" + zonedDateTime +
                '}';
    }
}
