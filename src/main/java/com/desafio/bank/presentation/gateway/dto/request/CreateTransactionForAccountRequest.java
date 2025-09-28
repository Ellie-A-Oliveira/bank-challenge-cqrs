package com.desafio.bank.presentation.gateway.dto.request;

import com.desafio.bank.domain.entity.aggregate.enumeration.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateTransactionForAccountRequest(
        BigDecimal amount,
        TransactionType transactionType,
        UUID accountId
) { }
