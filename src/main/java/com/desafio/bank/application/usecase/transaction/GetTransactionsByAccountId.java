package com.desafio.bank.application.usecase.transaction;

import com.desafio.bank.domain.entity.view.TransactionView;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface GetTransactionsByAccountId {
    List<TransactionView> execute(UUID accountId);
}
