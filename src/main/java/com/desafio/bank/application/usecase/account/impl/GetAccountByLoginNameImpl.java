package com.desafio.bank.application.usecase.account.impl;

import com.desafio.bank.application.usecase.account.GetAccountByLoginName;
import com.desafio.bank.domain.entity.view.AccountView;
import com.desafio.bank.infrastructure.repository.AccountViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetAccountByLoginNameImpl implements GetAccountByLoginName {
    private final AccountViewRepository repository;

    @Override
    public Optional<AccountView> execute(String loginName) {
        return repository.findByLoginName(loginName);
    }
}
