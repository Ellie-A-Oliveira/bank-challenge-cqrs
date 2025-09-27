package com.desafio.bank.domain.entity.aggregate;

import com.desafio.bank.domain.entity.aggregate.enumeration.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Aggregate
@Data
@NoArgsConstructor
public class Transaction {
    @AggregateIdentifier
    private UUID transactionId;

    private BigDecimal amount;
    private Instant createdAt;

    private TransactionType transactionType;

    @AggregateMember
    private Account account;
}
