package com.desafio.bank.presentation.gateway.dto.request;

public record LoginToAccountRequest(
        String loginName,
        String password
) { }
