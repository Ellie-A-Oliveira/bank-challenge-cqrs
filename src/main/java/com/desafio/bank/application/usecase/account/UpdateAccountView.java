package com.desafio.bank.application.usecase.account;

import com.desafio.bank.domain.entity.view.AccountView;
import org.springframework.stereotype.Service;

@Service
public interface UpdateAccountView {
    AccountView execute(AccountView updatedAccountView);
}
