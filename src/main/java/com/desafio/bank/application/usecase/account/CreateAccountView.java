package com.desafio.bank.application.usecase.account;

import com.desafio.bank.domain.entity.view.AccountView;
import org.springframework.stereotype.Service;

@Service
public interface CreateAccountView {
    AccountView execute(AccountView accountViewToBeCreated);
}
