package com.desafio.bank.application.usecase.transaction;

import com.desafio.bank.domain.entity.view.TransactionView;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface GetTransactionById {
    Optional<TransactionView> execute(UUID transactionId);
}
