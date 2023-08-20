package com.example.demo.valueObject;

import com.example.demo.Identifier;
import com.example.demo.ValueObject;

import java.util.Objects;
import java.util.UUID;

public class CustomerId extends Identifier {
    private final String value;


    public CustomerId(String value) {
        Objects.requireNonNull(value, "Id should not be null");
        this.value = value;
    }

    public static String uuid() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }

    @Override
    public String getValue() {
        return value;
    }
}
