package com.desafio.bank.application.usecase.transaction.impl;

import com.desafio.bank.application.usecase.transaction.CreateTransactionView;
import com.desafio.bank.domain.entity.view.TransactionView;
import com.desafio.bank.infrastructure.repository.TransactionViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTransactionViewImpl implements CreateTransactionView {
    private final TransactionViewRepository repository;

    @Override
    public TransactionView execute(TransactionView transactionViewToBeCreated) {
        return repository.save(transactionViewToBeCreated);
    }
}
