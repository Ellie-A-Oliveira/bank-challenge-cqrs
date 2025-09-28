package com.desafio.bank.application.projection.account;

import com.desafio.bank.domain.entity.view.AccountView;
import com.desafio.bank.domain.event.account.AccountCreatedEvent;
import com.desafio.bank.infrastructure.repository.AccountViewRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountProjection {
    private final AccountViewRepository repository;

    // Use cases

    @EventHandler
    public void on(AccountCreatedEvent evt) {
        AccountView accountView = AccountView.builder()
                .accountId(evt.accountId())
                .fullName(evt.fullName())
                .document(evt.document())
                .loginName(evt.loginName())
                .passwordHash(evt.passwordHash())
                .amount(evt.amount())
                .build();

        repository.save(accountView);
    }
}
