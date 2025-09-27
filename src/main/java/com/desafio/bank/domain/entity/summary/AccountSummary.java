package com.desafio.bank.domain.entity.summary;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountSummary {
    @Id
    private UUID accountId;

    private BigDecimal amount;

    private CustomerSummary customer;

    private List<TransactionSummary> transactionsList;
}
