package com.example.demo.aggregate;


import com.example.demo.commands.CreateCustomerCommand;
import com.example.demo.entity.User;
import com.example.demo.events.CustomerCreated;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.valueObject.CustomerId;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CustomerAggregateRoot extends Aggregate<CustomerId> {

    public final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "customer-topic";

    private User userEntity;

    public CustomerAggregateRoot(CustomerId customerId, KafkaTemplate<String, Object> kafkaTemplate, CreateCustomerCommand command) {
        super(customerId);
        this.kafkaTemplate = kafkaTemplate;
        this.userEntity = mapUser(command);
    }

    public void createCustomer(CustomerRepository customerRepository) throws JsonProcessingException {
        customerRepository.save(userEntity);
        CustomerCreated customerCreatedEvent = new CustomerCreated(userEntity, LocalDateTime.now());
        kafkaTemplate.send(TOPIC, customerCreatedEvent);
    }

    public User mapUser(CreateCustomerCommand command) {
        return User.builder()
                .id(this.getId().getValue())
                .email(command.email())
                .balance(BigDecimal.ZERO)
                .document(command.document())
                .firstName(command.firstName())
                .lastName(command.lastName())
                .build();
    }
}
