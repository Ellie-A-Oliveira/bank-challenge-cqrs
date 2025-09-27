package com.desafio.bank.domain.entity.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@Data
@NoArgsConstructor
public class Customer {
    @AggregateIdentifier
    private UUID customerId;

    private String fullName;
    private String document;
    private String loginName;
    private String passwordHash;

}
