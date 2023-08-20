package com.example.demoquery.service;

import com.example.demoquery.projection.TransferSummaryProjection;
import com.example.demoquery.repository.TransferSummaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferSummaryQueryService {

    private final TransferSummaryRepository repository;

    public TransferSummaryQueryService(TransferSummaryRepository repository) {
        this.repository = repository;
    }

    public List<TransferSummaryProjection> transferSummaryProjection() {
        return repository.findAll()
                .stream().map(v -> {
                    var projection = new TransferSummaryProjection();
                    projection.setFromUserId(v.getUserFromId());
                    projection.setTotalAmount(v.getTransferAmount());
                    projection.setToUserId(v.getUserToId());
                    return projection;
                }).collect(Collectors.toList());
    }
}
