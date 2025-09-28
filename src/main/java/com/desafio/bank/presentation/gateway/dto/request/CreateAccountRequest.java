package com.desafio.bank.presentation.gateway.dto.request;

public record CreateAccountRequest(
        String fullName,
        String document,
        String loginName,
        String passwordHash
) { }
