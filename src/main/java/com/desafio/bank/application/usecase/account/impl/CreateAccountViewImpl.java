package com.desafio.bank.application.usecase.account.impl;

import com.desafio.bank.application.usecase.account.CreateAccountView;
import com.desafio.bank.domain.entity.view.AccountView;
import com.desafio.bank.infrastructure.repository.AccountViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAccountViewImpl implements CreateAccountView {
    private final AccountViewRepository repository;

    @Override
    public AccountView execute(AccountView accountViewToBeCreated) {
        return repository.save(accountViewToBeCreated);
    }
}
