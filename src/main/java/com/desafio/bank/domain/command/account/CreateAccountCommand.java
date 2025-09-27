package com.desafio.bank.domain.command.account;


import java.math.BigDecimal;
import java.util.UUID;

public record CreateAccountCommand(
        BigDecimal amount,
        UUID customerId
) { }
