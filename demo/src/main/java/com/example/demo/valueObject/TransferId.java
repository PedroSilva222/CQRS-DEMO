package com.example.demo.valueObject;

import com.example.demo.Identifier;

import java.util.Objects;
import java.util.UUID;

public class TransferId extends Identifier {
    private final String value;

    public TransferId(String aId) {
        Objects.requireNonNull(aId, "Id should not be null");
        this.value = aId;
    }


    public static String uuid() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }


    @Override
    public String getValue() {
        return this.value;
    }
}
