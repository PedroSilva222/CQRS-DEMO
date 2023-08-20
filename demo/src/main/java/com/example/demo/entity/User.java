package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "customer")
@Entity
public class User {

    @Id
    private String id;

    @Column(name = "document_id")
    private String document;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;

    private BigDecimal balance;

    @OneToMany(mappedBy = "userFrom")
    @JsonIgnore
    private List<Transfer> transfersFrom;

    @OneToMany(mappedBy = "userTo")
    @JsonIgnore
    private List<Transfer> transfersTo;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", document='" + document + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                '}';
    }
}
