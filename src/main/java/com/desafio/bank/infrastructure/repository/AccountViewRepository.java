package com.desafio.bank.infrastructure.repository;

import com.desafio.bank.domain.entity.view.AccountView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountViewRepository extends JpaRepository<AccountView, UUID> {
    Optional<AccountView> findByLoginName(String loginName);
}
