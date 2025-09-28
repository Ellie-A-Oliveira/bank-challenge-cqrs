package com.desafio.bank.infrastructure.repository;


import com.desafio.bank.domain.entity.view.AccountView;
import com.desafio.bank.domain.entity.view.TransactionView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionViewRepository extends JpaRepository<TransactionView, UUID> {
    List<TransactionView> findByAccount_AccountId(UUID accountId);
}
