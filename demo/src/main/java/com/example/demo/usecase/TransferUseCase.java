package com.example.demo.usecase;

import com.example.demo.aggregate.TransferAggregateRoot;
import com.example.demo.commands.CreateTransferCommand;
import com.example.demo.entity.Transfer;
import com.example.demo.entity.User;
import com.example.demo.repository.BankTransferRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.valueObject.TransferId;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Component
public class TransferUseCase {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final CustomerService customerService;

    private final BankTransferRepository bankTransferRepository;


    public TransferUseCase(KafkaTemplate<String, Object> kafkaTemplate, CustomerService customerService, BankTransferRepository bankTransferRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.customerService = customerService;
        this.bankTransferRepository = bankTransferRepository;
    }

    @Transactional
    public void handler(final CreateTransferCommand command) throws JsonProcessingException {
        var users = validateUsersTransaction(command.userFrom(), command.userTo());
        Transfer transferEntity = Transfer.builder()
                .amount(command.amount())
                .userTo(users.get("userTo"))
                .userFrom(users.get("userFrom"))
                .build();

        TransferAggregateRoot aggregateRoot = new TransferAggregateRoot(new TransferId(TransferId.uuid()), kafkaTemplate, transferEntity, bankTransferRepository);
        aggregateRoot.generatePayment();
    }

    public Map<String, User> validateUsersTransaction(String idFrom, String idTo) {
        Map<String, User> usersTransaction = new HashMap<>();

        var customerFrom = customerService.findUserById(idFrom)
                .orElseThrow
                        (() -> new IllegalStateException("User From doenst exists in the database"));

        var customerTo = customerService.findUserById(idTo)
                .orElseThrow
                        (() -> new IllegalStateException("User To doenst exists in the database"));

        usersTransaction.put("userFrom", customerFrom);
        usersTransaction.put("userTo", customerTo);

        return usersTransaction;
    }
}
