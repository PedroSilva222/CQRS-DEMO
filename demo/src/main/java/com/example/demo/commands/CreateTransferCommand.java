package com.example.demo.commands;

import java.math.BigDecimal;

public record CreateTransferCommand(String userTo, String userFrom, BigDecimal amount) { }
