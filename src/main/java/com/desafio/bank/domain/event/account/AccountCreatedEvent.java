package com.desafio.bank.domain.event.account;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountCreatedEvent(
        UUID accountId,
        String fullName,
        String document,
        String loginName,
        String passwordHash,
        BigDecimal balance
) { }
