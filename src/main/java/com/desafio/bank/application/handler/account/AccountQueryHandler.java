package com.desafio.bank.application.handler.account;

import com.desafio.bank.application.usecase.account.GetAccountById;
import com.desafio.bank.domain.entity.view.AccountView;
import com.desafio.bank.domain.exception.AccountViewNotFoundException;
import com.desafio.bank.domain.query.account.GetAccountByIdQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountQueryHandler {
    private final RedisTemplate<String, AccountView> redisTemplate;

    // Use cases
    private final GetAccountById getAccountById;

    @QueryHandler
    public AccountView handle(GetAccountByIdQuery query) {
        AccountView accountView = redisTemplate.opsForValue().get(query.accountId());

        if (accountView == null) {
            return getAccountById.execute(query.accountId())
                    .orElseThrow(() -> new AccountViewNotFoundException("Account not found with ID " + query.accountId()));
        }

        return accountView;
    }
}
