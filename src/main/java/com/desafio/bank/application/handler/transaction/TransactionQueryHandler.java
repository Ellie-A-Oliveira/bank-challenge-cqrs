package com.desafio.bank.application.handler.transaction;

import com.desafio.bank.application.usecase.account.GetAccountById;
import com.desafio.bank.application.usecase.transaction.GetTransactionById;
import com.desafio.bank.application.usecase.transaction.GetTransactionsByAccountId;
import com.desafio.bank.domain.entity.view.AccountView;
import com.desafio.bank.domain.entity.view.TransactionView;
import com.desafio.bank.domain.exception.AccountViewNotFoundException;
import com.desafio.bank.domain.exception.TransactionViewNotFoundException;
import com.desafio.bank.domain.query.account.GetAccountByIdQuery;
import com.desafio.bank.domain.query.transaction.GetTransactionByIdQuery;
import com.desafio.bank.domain.query.transaction.GetTransactionsByAccountIdQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionQueryHandler {
    private final RedisTemplate<String, TransactionView> redisTemplate;

    // Use cases
    private final GetTransactionById getTransactionById;
    private final GetTransactionsByAccountId getTransactionsByAccountId;

    @QueryHandler
    public TransactionView handle(GetTransactionByIdQuery query) {
        TransactionView transactionView = redisTemplate.opsForValue().get(query.transactionId().toString());

        if (transactionView == null) {
            return getTransactionById.execute(query.transactionId())
                    .orElseThrow(() -> new TransactionViewNotFoundException("Transaction not found with ID " + query.transactionId()));
        }

        return transactionView;
    }

    @QueryHandler
    public List<TransactionView> handle(GetTransactionsByAccountIdQuery query) {
        List<TransactionView> transactionViews = redisTemplate
                .opsForList()
                .range("account-transactions:" + query.accountId().toString(),0, -1);

        if (transactionViews != null && transactionViews.isEmpty()) {
            return getTransactionsByAccountId.execute(query.accountId());
        }

        return transactionViews;
    }
}
