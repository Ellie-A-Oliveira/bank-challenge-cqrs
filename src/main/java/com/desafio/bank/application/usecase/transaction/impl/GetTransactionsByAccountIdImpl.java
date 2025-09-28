package com.desafio.bank.application.usecase.transaction.impl;

import com.desafio.bank.application.usecase.transaction.GetTransactionsByAccountId;
import com.desafio.bank.domain.entity.view.TransactionView;
import com.desafio.bank.infrastructure.repository.TransactionViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetTransactionsByAccountIdImpl implements GetTransactionsByAccountId {
    private final TransactionViewRepository repository;

    @Override
    public List<TransactionView> execute(UUID accountId) {
        return repository.findByAccount_AccountId(accountId);
    }
}
