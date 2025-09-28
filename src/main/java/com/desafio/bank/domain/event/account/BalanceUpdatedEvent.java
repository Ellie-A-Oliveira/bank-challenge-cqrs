package com.desafio.bank.domain.event.account;

import com.desafio.bank.domain.entity.aggregate.enumeration.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

public record BalanceUpdatedEvent(
        UUID accountId,
        BigDecimal balance,
        TransactionType type
) { }
