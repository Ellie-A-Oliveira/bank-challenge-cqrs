package com.desafio.bank.domain.command.account;


import com.fasterxml.jackson.annotation.JsonIgnore;

public record CreateAccountCommand(
        String fullName,
        String document,
        String loginName,
        String password
) { }
