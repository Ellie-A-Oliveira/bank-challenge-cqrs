package com.desafio.bank.application.usecase.account.impl;

import com.desafio.bank.application.usecase.account.GetAccountById;
import com.desafio.bank.domain.entity.view.AccountView;
import com.desafio.bank.infrastructure.repository.AccountViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAccountByIdImpl implements GetAccountById {
    private final AccountViewRepository repository;

    @Override
    public Optional<AccountView> execute(UUID id) {
        return repository.findById(id);
    }
}
