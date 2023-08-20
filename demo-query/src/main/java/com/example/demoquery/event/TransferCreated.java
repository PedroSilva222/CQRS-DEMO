package com.example.demoquery.event;

import com.example.demoquery.projection.Transfer;

import java.time.LocalDateTime;


public class TransferCreated {
    private  Transfer transfer;
    private LocalDateTime zonedDateTime;


    public TransferCreated(Transfer transfer, LocalDateTime zonedDateTime) {
        this.transfer = transfer;
        this.zonedDateTime = zonedDateTime;
    }

    public TransferCreated() {

    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public void setZonedDateTime(LocalDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public LocalDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
