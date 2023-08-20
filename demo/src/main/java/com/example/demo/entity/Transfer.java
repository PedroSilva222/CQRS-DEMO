package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transfer {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_from")
    private User userFrom;

    @ManyToOne
    @JoinColumn(name = "user_to")
    private User userTo;

    private BigDecimal amount;

    @Override
    public String toString() {
        return "Transfer{" +
                "id='" + id + '\'' +
                ", userFrom=" + userFrom +
                ", userTo=" + userTo +
                ", amount=" + amount +
                '}';
    }
}
