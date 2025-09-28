package com.desafio.bank.domain.entity.aggregate;

import com.desafio.bank.domain.command.account.CreateAccountCommand;
import com.desafio.bank.domain.event.account.AccountCreatedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
@NoArgsConstructor
public class Account {
    @AggregateIdentifier
    private UUID accountId;

    private String fullName;
    private String document;
    private String loginName;
    private String passwordHash;
    private BigDecimal amount;

    @CommandHandler
    public Account(CreateAccountCommand cmd) {
        BigDecimal initialAmount = BigDecimal.ZERO;
        UUID accountId = UUID.randomUUID();

        apply(new AccountCreatedEvent(
                accountId,
                cmd.fullName(),
                cmd.document(),
                cmd.loginName(),
                cmd.passwordHash(),
                initialAmount
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent evt) {
        accountId = evt.accountId();
        fullName = evt.fullName();
        document = evt.document();
        loginName = evt.loginName();
        passwordHash = evt.passwordHash();
        amount = evt.amount();
    }
}
