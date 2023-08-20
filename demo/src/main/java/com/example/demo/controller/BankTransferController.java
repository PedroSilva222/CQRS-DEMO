package com.example.demo.controller;

import com.example.demo.commands.CreateTransferCommand;
import com.example.demo.dto.TransferDto;
import com.example.demo.usecase.TransferUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transfer")
public class BankTransferController {

    public final TransferUseCase transferUseCase;

    public BankTransferController(TransferUseCase transferUseCase) {
        this.transferUseCase = transferUseCase;
    }

    @PostMapping
    public ResponseEntity<?> doTransfer(@RequestBody final TransferDto transferDto) {
        final CreateTransferCommand command = new CreateTransferCommand(
                transferDto.idTo(),
                transferDto.idFrom(),
                transferDto.amount());

        try {
            transferUseCase.handler(command);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar Usuário: " + e.getMessage());
        }
    }
}
