package com.desafio.bank.application.usecase.account.impl;

import com.desafio.bank.application.usecase.account.UpdateAccountView;
import com.desafio.bank.domain.entity.view.AccountView;
import com.desafio.bank.infrastructure.repository.AccountViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAccountViewImpl implements UpdateAccountView {
    private final AccountViewRepository repository;

    @Override
    public AccountView execute(AccountView updatedAccountView) {
        return repository.save(updatedAccountView);
    }
}
