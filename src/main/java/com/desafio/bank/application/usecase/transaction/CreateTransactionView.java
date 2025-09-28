package com.desafio.bank.application.usecase.transaction;

import com.desafio.bank.domain.entity.view.AccountView;
import com.desafio.bank.domain.entity.view.TransactionView;
import org.springframework.stereotype.Service;

@Service
public interface CreateTransactionView {
    TransactionView execute(TransactionView transactionViewToBeCreated);
}
