package com.desafio.bank.domain.event.transaction;

import com.desafio.bank.domain.entity.aggregate.enumeration.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionCreatedEvent(
        UUID transactionId,
        BigDecimal amount,
        Instant timestamp,
        TransactionType transactionType,
        UUID accountId
) { }
