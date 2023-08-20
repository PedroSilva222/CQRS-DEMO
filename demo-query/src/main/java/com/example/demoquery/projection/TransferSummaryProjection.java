package com.example.demoquery.projection;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferSummaryProjection {
    private String fromUserId;
    private String toUserId;
    private BigDecimal totalAmount;
    // getters e setters
}