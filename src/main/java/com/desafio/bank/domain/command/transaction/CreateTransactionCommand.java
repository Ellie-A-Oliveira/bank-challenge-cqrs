package com.desafio.bank.domain.command.transaction;

import com.desafio.bank.domain.entity.aggregate.enumeration.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateTransactionCommand (
        BigDecimal amount,
        TransactionType transactionType,
        UUID accountId
) { }
