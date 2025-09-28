package com.desafio.bank.presentation.gateway.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record CreateAccountRequest(
        String fullName,
        String document,
        String loginName,
        String password
) { }
