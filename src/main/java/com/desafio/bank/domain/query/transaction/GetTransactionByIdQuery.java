package com.desafio.bank.domain.query.transaction;

import java.util.UUID;

public record GetTransactionByIdQuery(
        UUID transactionId
) { }
