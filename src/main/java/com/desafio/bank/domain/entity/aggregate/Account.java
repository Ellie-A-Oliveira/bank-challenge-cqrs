package com.desafio.bank.domain.entity.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Aggregate
@Data
@NoArgsConstructor
public class Account {
    @AggregateIdentifier
    private UUID accountId;

    private BigDecimal amount;

    @AggregateMember
    private Customer customer;

    @AggregateMember
    private List<Transaction> transactions;
}
