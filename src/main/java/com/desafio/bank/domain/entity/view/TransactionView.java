package com.desafio.bank.domain.entity.view;

import com.desafio.bank.domain.entity.aggregate.Account;
import com.desafio.bank.domain.entity.aggregate.enumeration.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionView {
    @Id
    private UUID transactionId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Instant timestamp;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne
    private AccountView account;
}
