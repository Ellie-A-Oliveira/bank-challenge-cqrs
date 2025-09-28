package com.desafio.bank.application.projection.transaction;

import com.desafio.bank.application.usecase.account.GetAccountById;
import com.desafio.bank.application.usecase.transaction.CreateTransactionView;
import com.desafio.bank.domain.entity.view.AccountView;
import com.desafio.bank.domain.entity.view.TransactionView;
import com.desafio.bank.domain.event.transaction.TransactionCreatedEvent;
import com.desafio.bank.domain.exception.AccountViewNotFoundException;
import com.desafio.bank.infrastructure.repository.TransactionViewRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionProjection {
    private final RedisTemplate<String, TransactionView> redisTemplate;

    // Use cases
    private final GetAccountById getAccountById;
    private final CreateTransactionView createTransactionView;

    @EventHandler
    public void on(TransactionCreatedEvent evt) {
        AccountView accountView = getAccountById.execute(evt.accountId())
                .orElseThrow(() -> new AccountViewNotFoundException("Account not found with ID " + evt.accountId()));

        TransactionView transactionView = TransactionView.builder()
                .transactionId(evt.transactionId())
                .amount(evt.amount())
                .timestamp(evt.timestamp())
                .transactionType(evt.transactionType())
                .account(accountView)
                .build();

        createTransactionView.execute(transactionView);
        redisTemplate.opsForValue().set("transaction:" + evt.transactionId(), transactionView);
        redisTemplate.opsForList().rightPush("account-transactions:" + evt.accountId(), transactionView);
    }
}
