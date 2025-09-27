package com.desafio.bank.domain.command.customer;

public record CreateCustomerCommand (
        String fullName,
        String document,
        String loginName,
        String password
) { }
