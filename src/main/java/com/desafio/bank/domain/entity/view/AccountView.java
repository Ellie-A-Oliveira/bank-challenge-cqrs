package com.desafio.bank.domain.entity.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountView {
    @Id
    private UUID accountId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String document;

    @Column(nullable = false)
    private String loginName;

    @Column(nullable = false)
    @JsonIgnore
    private String passwordHash;

    @Column(nullable = false)
    private BigDecimal amount;

    @OneToMany(fetch = FetchType.EAGER)
    private List<TransactionView> transactionsList;
}
