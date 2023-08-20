package com.example.demoquery.controller;

import com.example.demoquery.service.TransferSummaryQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transfer/view")
public class TransferController {

    private final TransferSummaryQueryService transferSummaryQueryService;

    public TransferController(TransferSummaryQueryService transferSummaryQueryService) {
        this.transferSummaryQueryService = transferSummaryQueryService;
    }

    @GetMapping
    public ResponseEntity<?> getSummaries() {
        return ResponseEntity.ok(transferSummaryQueryService.transferSummaryProjection());
    }
}
