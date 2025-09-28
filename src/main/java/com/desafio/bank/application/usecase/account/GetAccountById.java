package com.desafio.bank.application.usecase.account;

import com.desafio.bank.domain.entity.view.AccountView;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface GetAccountById {
    Optional<AccountView> execute(UUID id);
}
