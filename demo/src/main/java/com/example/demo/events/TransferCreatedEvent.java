package com.example.demo.events;

import com.example.demo.entity.Transfer;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class TransferCreatedEvent {
    private final Transfer transfer;
    private final LocalDateTime zonedDateTime;

    public TransferCreatedEvent(Transfer transfer, LocalDateTime zonedDateTime) {
        this.transfer = transfer;
        this.zonedDateTime = zonedDateTime;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public LocalDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    @Override
    public String toString() {
        return "TransferCreatedEvent{" +
                "transfer=" + transfer +
                ", zonedDateTime=" + zonedDateTime +
                '}';
    }
}
