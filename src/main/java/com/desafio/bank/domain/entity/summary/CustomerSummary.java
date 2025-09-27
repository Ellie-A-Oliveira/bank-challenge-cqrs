package com.desafio.bank.domain.entity.summary;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSummary {
    @Id
    private UUID customerId;

    private String fullName;
    private String document;
    private String loginName;
    private String passwordHash;
}
