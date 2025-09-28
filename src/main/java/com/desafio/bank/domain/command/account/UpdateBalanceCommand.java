package com.desafio.bank.domain.command.account;

import com.desafio.bank.domain.entity.aggregate.enumeration.TransactionType;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateBalanceCommand(
        @TargetAggregateIdentifier UUID accountId,
        BigDecimal amount,
        TransactionType type
) { }
