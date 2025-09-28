package com.desafio.bank.application.usecase.account;

import com.desafio.bank.domain.entity.view.AccountView;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface GetAccountByLoginName {
    Optional<AccountView> execute(String loginName);
}
