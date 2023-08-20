package com.example.demo.commands;


public record CreateCustomerCommand(String firstName, String lastName, String email, String document) { }
