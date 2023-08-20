package com.example.demo.aggregate;

public abstract class Aggregate<ID> {
    private ID id;

    public Aggregate(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
