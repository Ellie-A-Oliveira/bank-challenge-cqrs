package com.desafio.bank.domain.command.account;


import java.math.BigDecimal;
import java.util.UUID;

public record CreateAccountCommand(
        String fullName,
        String document,
        String loginName,
        String passwordHash
) { }
