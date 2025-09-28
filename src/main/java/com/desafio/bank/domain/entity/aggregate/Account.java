package com.desafio.bank.domain.entity.aggregate;

import com.desafio.bank.domain.command.account.CreateAccountCommand;
import com.desafio.bank.domain.command.account.UpdateBalanceCommand;
import com.desafio.bank.domain.entity.aggregate.enumeration.TransactionType;
import com.desafio.bank.domain.event.account.AccountCreatedEvent;
import com.desafio.bank.domain.event.account.BalanceUpdatedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
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
    private BigDecimal balance;

    @CommandHandler
    public Account(CreateAccountCommand cmd) {
        BigDecimal initialBalance = BigDecimal.ZERO;
        UUID accountId = UUID.randomUUID();

        apply(new AccountCreatedEvent(
                accountId,
                cmd.fullName(),
                cmd.document(),
                cmd.loginName(),
                cmd.password(),
                initialBalance
        ));
    }

    @CommandHandler
    public void handle(UpdateBalanceCommand cmd) {
        switch (cmd.type()) {
            case DEPOSIT -> {
                BigDecimal interest = new BigDecimal("1.02");

                BigDecimal newBalance;
                if (getBalance().compareTo(BigDecimal.ZERO) < 0) {
                    BigDecimal negativeBalance = getBalance().multiply(interest);
                    newBalance = negativeBalance.add(cmd.amount());
                } else {
                    newBalance = getBalance().add(cmd.amount());
                }

                apply(new BalanceUpdatedEvent(
                        cmd.accountId(),
                        newBalance,
                        cmd.type()
                ));
            }
            case PAYMENT, WITHDRAWAL -> {
                BigDecimal newBalance = getBalance().subtract(cmd.amount());

                apply(new BalanceUpdatedEvent(
                        cmd.accountId(),
                        newBalance,
                        cmd.type()
                ));
            }
            default -> throw new IllegalArgumentException("Invalid transaction type: " + cmd.type());
        }
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent evt) {
        accountId = evt.accountId();
        fullName = evt.fullName();
        document = evt.document();
        loginName = evt.loginName();
        passwordHash = evt.passwordHash();
        balance = evt.balance();
    }

    @EventSourcingHandler
    public void on(BalanceUpdatedEvent evt) {
        balance = evt.balance();
    }
}
