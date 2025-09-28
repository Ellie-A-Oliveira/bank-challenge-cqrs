package com.desafio.bank.domain.entity.aggregate;

import com.desafio.bank.domain.command.transaction.CreateTransactionCommand;
import com.desafio.bank.domain.entity.aggregate.enumeration.TransactionType;
import com.desafio.bank.domain.event.account.AccountCreatedEvent;
import com.desafio.bank.domain.event.transaction.TransactionCreatedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
@NoArgsConstructor
public class Transaction {
    @AggregateIdentifier
    private UUID transactionId;

    private BigDecimal amount;
    private Instant timestamp;

    private TransactionType transactionType;

    private UUID accountId;

    @CommandHandler
    public Transaction(CreateTransactionCommand cmd) {
        UUID transactionId = UUID.randomUUID();
        Instant timestamp = Instant.now();

        apply(new TransactionCreatedEvent(
                transactionId,
                cmd.amount(),
                timestamp,
                cmd.transactionType(),
                cmd.accountId()
        ));
    }

    @EventSourcingHandler
    public void on(TransactionCreatedEvent evt) {
        transactionId = evt.transactionId();
        amount = evt.amount();
        timestamp = evt.timestamp();
        transactionType = evt.transactionType();
        accountId = evt.accountId();
    }
}
