package com.example.demoquery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "transfer_summary_view")
@Getter
@Setter
public class TransferSummary {
    @Id
    @Column(name = "transfer_id")
    private String transferId;

    @Column(name = "user_from_id")
    private String userFromId;

    @Column(name = "user_from_first_name")
    private String userFromFirstName;

    @Column(name = "user_from_last_name")
    private String userFromLastName;

    @Column(name = "user_to_id")
    private String userToId;

    @Column(name = "user_to_first_name")
    private String userToFirstName;

    @Column(name = "user_to_last_name")
    private String userToLastName;

    @Column(name = "transfer_amount")
    private BigDecimal transferAmount;
}
