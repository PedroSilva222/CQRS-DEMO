package com.example.demo.aggregate;

import com.example.demo.entity.Transfer;
import com.example.demo.events.TransferCreatedEvent;
import com.example.demo.repository.BankTransferRepository;
import com.example.demo.valueObject.TransferId;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

public class TransferAggregateRoot extends Aggregate<TransferId> {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private  Transfer transfer;

    private final String TOPIC = "transfer-topic";

    private final BankTransferRepository repository;

    public TransferAggregateRoot(TransferId transferId, KafkaTemplate<String, Object> kafkaTemplate, Transfer transfer, BankTransferRepository repository) {
        super(transferId);
        this.kafkaTemplate = kafkaTemplate;
        this.transfer = transfer;
        this.repository = repository;
    }


    public void generatePayment() throws JsonProcessingException {
        transfer.setId(this.getId().getValue());
        repository.save(transfer);
        TransferCreatedEvent transferCreatedEvent = new TransferCreatedEvent(transfer, LocalDateTime.now());
        kafkaTemplate.send(TOPIC, transferCreatedEvent);
    }
}
