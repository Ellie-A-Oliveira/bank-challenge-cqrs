package com.desafio.bank.application.usecase.transaction.impl;

import com.desafio.bank.application.usecase.transaction.GetTransactionById;
import com.desafio.bank.domain.entity.view.TransactionView;
import com.desafio.bank.infrastructure.repository.TransactionViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetTransactionByIdImpl implements GetTransactionById {
    private final TransactionViewRepository repository;

    @Override
    public Optional<TransactionView> execute(UUID transactionId) {
        return repository.findById(transactionId);
    }
}
