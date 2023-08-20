package com.example.demo.usecase;


import com.example.demo.aggregate.CustomerAggregateRoot;
import com.example.demo.commands.CreateCustomerCommand;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.valueObject.CustomerId;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerUseCase {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final CustomerRepository customerRepository;


    public CustomerUseCase(KafkaTemplate<String, Object> kafkaTemplate, CustomerRepository customerRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.customerRepository = customerRepository;
    }

    public void execute(CreateCustomerCommand command) throws JsonProcessingException {
        if (customerRepository
                .existsByEmail(command.email())  ||  customerRepository
                .existsByDocument(command.document())) {
            throw new IllegalArgumentException("Email or CPF already in use." );
        }


        CustomerAggregateRoot aggregateRoot = new CustomerAggregateRoot(
                new CustomerId(CustomerId.uuid()),
                kafkaTemplate,
                command
                );

        aggregateRoot.createCustomer(this.customerRepository);

    }
}
