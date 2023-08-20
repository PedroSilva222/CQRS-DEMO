package com.example.demo.controller;


import com.example.demo.commands.CreateCustomerCommand;
import com.example.demo.dto.CreateCustomerRequestDto;
import com.example.demo.usecase.CustomerUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    public final CustomerUseCase customerUseCase;

    public CustomerController(CustomerUseCase customerUseCase) {
        this.customerUseCase = customerUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(
            final @RequestBody CreateCustomerRequestDto createCustomerRequestDto
    ) {
        final var command = new CreateCustomerCommand(
                createCustomerRequestDto.getFirstName(),
                createCustomerRequestDto.getLastName(),
                createCustomerRequestDto.getEmail(),
                createCustomerRequestDto.getDocument()
        );

        try {
            customerUseCase.execute(command);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar Usuário: " + e.getMessage());
        }
    }
}
