package com.desafio.bank.application.projection.account;

import com.desafio.bank.application.usecase.account.CreateAccountView;
import com.desafio.bank.application.usecase.account.GetAccountById;
import com.desafio.bank.application.usecase.account.UpdateAccountView;
import com.desafio.bank.domain.entity.view.AccountView;
import com.desafio.bank.domain.event.account.AccountCreatedEvent;
import com.desafio.bank.domain.event.account.BalanceUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountProjection {
    private final RedisTemplate<String, AccountView> redisTemplate;

    // Use cases
    private final CreateAccountView createAccountView;
    private final GetAccountById getAccountById;
    private final UpdateAccountView updateAccountView;

    @EventHandler
    public void on(AccountCreatedEvent evt) {
        AccountView accountView = AccountView.builder()
                .accountId(evt.accountId())
                .fullName(evt.fullName())
                .document(evt.document())
                .loginName(evt.loginName())
                .passwordHash(evt.passwordHash())
                .balance(evt.balance())
                .build();

        createAccountView.execute(accountView);
        redisTemplate.opsForValue().set("account:" + evt.accountId().toString(), accountView);
    }

    @EventHandler
    public void on(BalanceUpdatedEvent evt) {
        AccountView existingAccount = getAccountById.execute(evt.accountId())
                .orElseThrow();

        existingAccount.setBalance(evt.balance());
        updateAccountView.execute(existingAccount);
        redisTemplate.opsForValue().set("account:" + evt.accountId().toString(), existingAccount);
    }
}
