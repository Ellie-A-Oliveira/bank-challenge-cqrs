package com.desafio.bank.domain.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountView {
    @Id
    private UUID accountId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String document;

    @Column(nullable = false)
    private String loginName;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private BigDecimal amount;

    @OneToMany
    private List<TransactionView> transactionsList;
}
