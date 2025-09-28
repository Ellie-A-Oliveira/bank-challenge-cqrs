package com.desafio.bank.domain.query.account;

import java.util.UUID;

public record GetAccountByIdQuery(
        UUID accountId
) { }
